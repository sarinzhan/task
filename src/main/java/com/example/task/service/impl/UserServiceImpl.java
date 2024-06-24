package com.example.task.service.impl;

import com.example.task.entity.User;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.UsersRepository;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void create(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        try {
            usersRepository.save(user);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить пользователя");
        }
    }

    @Override
    public void editPassword(User sourceUser, String newPassword) {
        User user = usersRepository.findByUsername(sourceUser.getUsername())
                .orElseThrow(() -> new BaseLogicException("Не удалось найти пользователя"));
        if(sourceUser.getPassword().equals(user.getPassword())){
            sourceUser.setId(user.getId());
            sourceUser.setPassword(newPassword);
            usersRepository.updatePasswordById(user.getId(), newPassword );
        }else{
            throw new BaseLogicException("Указаны неверные данные");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> all = usersRepository.findAll();
        if(all.isEmpty()){
            throw new BaseLogicException("Не удалось найти пользователей");
        }
        return all;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти пользователя"));
    }

    @Override
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти пользователя"));
    }

    @Override
    public User findById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти пользователя"));
    }
}
