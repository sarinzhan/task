package com.example.task.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.task.exception.BaseLogicException;
import com.example.task.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public void saveFile(String keyName, MultipartFile file) {
        try {
            var putObjectResult = s3client.putObject(bucketName, keyName, file.getInputStream(), null);
            System.out.println(putObjectResult.getMetadata());
        }catch (IOException ex){
            throw new BaseLogicException(ex.getMessage());
        }
    }

    @Override
    public S3Object getFile(String keyName) {
        try {
            return s3client.getObject(bucketName, keyName);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось найти файл");
        }
    }

    @Override
    public String getPresignedUrl(String keyName) {
        try {
            Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 10);
            // 1000 - millisec in one sec
            return s3client.generatePresignedUrl(bucketName, keyName, expiration).toString();
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось найти файл");
        }
    }

    @Override
    public void removeByKey(String key) {
        try{
            if(isObjectExists(key)) {
                s3client.deleteObject(new DeleteObjectRequest(bucketName, key));
            }else{
                throw new BaseLogicException("Не удалось удалить файл");
            }
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось удалить файл");
        }
    }

    @Override
    public void removeAll() {
        try {
            ObjectListing objectListing = s3client.listObjects(bucketName);
            while (true) {
                Iterator<S3ObjectSummary> objIterator = objectListing.getObjectSummaries().iterator();
                while (objIterator.hasNext()) {
                    s3client.deleteObject(bucketName, objIterator.next().getKey());
                }

                // If the bucket contains many objects, this listObjects() call might not return
                // all of the objects in the first listing. Check to see whether the listing was  truncated.
                // If so, retrieve the next page of objects and delete them.
                if (objectListing.isTruncated()) {
                    objectListing = s3client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось удалить все файлы");
        }
    }
    private boolean isObjectExists(String key) {
        return s3client.listObjects(bucketName, key).getObjectSummaries().stream()
                .anyMatch(objectSummary -> objectSummary.getKey().equals(key));
    }
}
