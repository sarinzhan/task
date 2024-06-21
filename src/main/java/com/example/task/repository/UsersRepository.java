package com.example.task.repository;

import com.example.task.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users " +
            "SET first_name = :firstName," +
            "   last_name = :lastName, " +
            "   login = :login, " +
            "   password = :password " +
            "WHERE id = :id",
            nativeQuery = true)
    int update(Long id,String firstName, String lastName, String login, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users " +
            "SET password = :password " +
            "WHERE id = :id",
            nativeQuery = true)
    int updatePasswordById(Long id, String password);



    @Modifying
    @Transactional
    @Query(value = "insert into users (first_name, last_name, login, password) " +
            "values (:firstName, :lastName, :login, :password)",
            nativeQuery = true)
    int create(String firstName, String lastName, String login, String password);

    @Modifying
    @Transactional
    @Query(value = "delete from users where id = :user.id",
            nativeQuery = true)
    int deleteById(Users user);

    @Modifying
    @Transactional
    @Query(value = "select * from users where id = :id",
            nativeQuery = true)
    Users read(Long id);

    @Query(value = "select * from users", nativeQuery = true)
    List<Users> getAll();

    @Modifying
    @Transactional
    @Query(value = "select * from users where login = :login",
            nativeQuery = true)
    Optional<Users> findByLogin(String login);
}
