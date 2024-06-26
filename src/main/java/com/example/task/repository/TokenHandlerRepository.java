package com.example.task.repository;

import com.example.task.entity.TokenHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenHandlerRepository extends JpaRepository<TokenHandler, Long> {

    @Query("select t from token_handler t where t.user.id = :userId")
    TokenHandler getByUserId(Long userId);

    @Query("select t from token_handler t where t.token = :token")
    Optional<TokenHandler> getByToken(String token);
}
