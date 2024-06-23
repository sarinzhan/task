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
    @Query(value = "delete from users where id = :user.id",
            nativeQuery = true)
    int deleteById(User user);

    @Modifying
    @Transactional
    @Query(value = "select * from users where id = :id",
            nativeQuery = true)
    User read(Long id);

    @Query(value = "select * from users", nativeQuery = true)
    List<User> getAll();

    @Modifying
    @Transactional
    @Query(value = "select * from users where login = :username",
            nativeQuery = true)
    Optional<User> findByUsername(String username);
}
