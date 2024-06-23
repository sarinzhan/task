package com.example.task.repository;

import com.example.task.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users " +
            "SET password = :password " +
            "WHERE id = :id",
            nativeQuery = true)
    int updatePasswordById(Long id, String password);

    @Query("select u from users u where u.username = :username")
    Optional<User> findByUsername(String username);
}
