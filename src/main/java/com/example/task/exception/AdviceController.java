package com.example.task.exception;

import com.example.task.dto.CommonResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(BaseLogicException.class)
    public CommonResponseDto<Void> handleBaseException(BaseLogicException ex){
        return new CommonResponseDto<Void>()
                .setMessage(ex.getMessage())
                .setStatus(400);
    }
}
