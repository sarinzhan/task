package com.example.task.service.impl;

import com.example.task.entity.Users;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.UsersRepository;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    @Override
    public void create(Users user) {
        try {
//            return usersRepository.create(user);
            usersRepository.create(user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword());
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить пользователя");
        }
    }

    @Override
    public Boolean auth(Users sourceUser) {
        Users users = usersRepository.findByLogin(sourceUser.getLogin())
                .orElseThrow(() ->  new BaseLogicException("Неудачная попытка входа"));
        if(!users.getPassword().equals(sourceUser.getPassword())){
            throw new BaseLogicException("Неудачная попытка входа");
        }
        return true;
    }

    @Override
    public void editPassword(Users sourceUser,String newPassword) {
        Users users = usersRepository.findByLogin(sourceUser.getLogin())
                .orElseThrow(() -> new BaseLogicException("Не удалось найти пользователя"));
        if(sourceUser.getPassword().equals(users.getPassword())){
            sourceUser.setId(users.getId());
            sourceUser.setPassword(newPassword);
            usersRepository.updatePasswordById(users.getId(), newPassword );
        }else{
            throw new BaseLogicException("Указаны неверные данные");
        }
    }

    @Override
    public List<Users> getAll() {
        List<Users> all = usersRepository.getAll();
        if(all.isEmpty()){
            throw new BaseLogicException("Не удалось найти пользователей");
        }
        return all;
    }
}
