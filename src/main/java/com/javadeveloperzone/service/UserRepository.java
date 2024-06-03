package com.javadeveloperzone.service;

import com.javadeveloperzone.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Интерфейс репозитория для управления пользователями.
 * Этот интерфейс определяет методы для получения, создания, обновления и удаления пользователей.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Находит пользователя по логину.
     *
     * @param userLogin логин пользователя
     * @return объект пользователя, если найден, иначе - пустой Optional
     */
    Optional<User> getUserByUserLogin(String userLogin);

    /**
     * Обновляет имя пользователя с указанным идентификатором.
     *
     * @param userId        идентификатор пользователя
     * @param newUserName новое имя пользователя
     */
    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.userName = :newUserName WHERE user.userId = :userId")
    void updateUserNameById(@Param("userId") int userId, @Param("newUserName") String newUserName);

    /**
     * Обновляет логин пользователя с указанным идентификатором.
     *
     * @param userId         идентификатор пользователя
     * @param newUserLogin новый логин пользователя
     */
    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.userLogin = :newUserLogin WHERE user.userId = :userId")
    void updateUserLoginById(@Param("userId") int userId, @Param("newUserLogin") String newUserLogin);

    /**
     * Обновляет пароль пользователя с указанным идентификатором.
     *
     * @param userId             идентификатор пользователя
     * @param newUserPassword новый пароль пользователя
     */
    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.userPassword = :newUserPassword WHERE user.userId = :userId")
    void updateUserPasswordById(@Param("userId") int userId, @Param("newUserPassword") String newUserPassword);
}