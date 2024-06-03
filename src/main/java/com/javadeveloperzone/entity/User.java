package com.javadeveloperzone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

/**
 * Сущность "Пользователь"
 * Представляет информацию о пользователях системы управления аэропортом
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airport_user")
public class User {
    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    /**
     * Имя пользователя
     */
    private String userName;

    /**
     * Логин пользователя
     */
    private String userLogin;

    /**
     * Пароль пользователя
     */
    private String userPassword;

    /**
     * Конструктор пользователя с заданными именем, логином и паролем
     *
     * @param userName      Имя пользователя
     * @param userLogin     Логин пользователя
     * @param userPassword  Пароль пользователя
     */
    public User(String userName, String userLogin, String userPassword) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }
}