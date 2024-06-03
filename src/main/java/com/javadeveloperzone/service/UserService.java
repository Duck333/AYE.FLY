package com.javadeveloperzone.service;
import com.javadeveloperzone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для работы с пользователями.
 * Этот класс предоставляет методы для управления пользователями, включая получение, создание, обновление и удаление.
 */
@Service
public class UserService {
    private static UserRepository userRepository;

    /**
     * Конструктор, инжектирующий репозиторий пользователей.
     *
     * @param userRepository репозиторий пользователей
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }

    /**
     * Получает список всех пользователей.
     *
     * @return список пользователей
     */
    public static List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Удаляет всех пользователей.
     */
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param userId идентификатор пользователя
     */
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Создает нового пользователя.
     *
     * @param user новый пользователь
     * @return созданный пользователь
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Обновляет имя пользователя по идентификатору.
     *
     * @param userId        идентификатор пользователя
     * @param newUserName новое имя пользователя
     */
    public void updateUserNameById(int userId, String newUserName) {
        userRepository.updateUserNameById(userId, newUserName);
    }

    /**
     * Обновляет логин пользователя по идентификатору.
     *
     * @param userId         идентификатор пользователя
     * @param newUserLogin новый логин пользователя
     */
    public void updateUserLoginById(int userId, String newUserLogin) {
        userRepository.updateUserLoginById(userId, newUserLogin);
    }

    /**
     * Обновляет пароль пользователя по идентификатору.
     *
     * @param userId             идентификатор пользователя
     * @param newUserPassword новый пароль пользователя
     */
    public void updateUserPasswordById(int userId, String newUserPassword) {
        userRepository.updateUserPasswordById(userId, newUserPassword);
    }

    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return объект пользователя, если найден, иначе - пустой Optional
     */
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    /**
     * Получает пользователя по логину.
     *
     * @param userLogin логин пользователя
     * @return объект пользователя, если найден, иначе - пустой Optional
     */
    public Optional<User> getUserByLogin(String userLogin) {
        return userRepository.getUserByUserLogin(userLogin);
    }
}