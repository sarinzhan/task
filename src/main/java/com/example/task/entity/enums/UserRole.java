package com.example.task.entity.enums;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority{
    ADMIN,
    MANAGER;
    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
