package com.example.task.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "token_handler")
@Data
public class TokenHandler{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt;

    private String token;

    @Column(name = "is_applied")
    private Boolean isApplied;
}
