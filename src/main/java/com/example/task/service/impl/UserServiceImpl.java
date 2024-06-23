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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UsersRepository usersRepository;
    @Override
    public void create(User user) {
        try {
//            return usersRepository.create(user);
            usersRepository.create(user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword());
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить пользователя");
        }
    }

    @Override
    public Boolean auth(User sourceUser) {
        User user = usersRepository.findByUsername(sourceUser.getLogin())
                .orElseThrow(() ->  new BaseLogicException("Неудачная попытка входа"));
        if(!user.getPassword().equals(sourceUser.getPassword())){
            throw new BaseLogicException("Неудачная попытка входа");
        }
        return true;
    }

    @Override
    public void editPassword(User sourceUser, String newPassword) {
        User user = usersRepository.findByUsername(sourceUser.getLogin())
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
        List<User> all = usersRepository.getAll();
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
}
