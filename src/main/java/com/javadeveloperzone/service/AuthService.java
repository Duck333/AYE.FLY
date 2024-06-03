package com.javadeveloperzone.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Сервис для аутентификации пользователей и администраторов
 */
@Service
public class AuthService {

    /**
     * Экземпляр JdbcTemplate для работы с базой данных
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Идентификатор текущего аутентифицированного пользователя
     */
    @Getter
    private int currentUserId;

    /**
     * Конструктор сервиса аутентификации
     *
     * @param jdbcTemplate Экземпляр JdbcTemplate для работы с базой данных
     */
    @Autowired
    public AuthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Метод для аутентификации пользователя или администратора
     *
     * @param login    Логин пользователя или администратора
     * @param password Пароль пользователя или администратора
     * @return Код результата аутентификации:
     *         0 - Пользователь/Администратор не найден
     *         1 - Пользователь
     *         2 - Администратор
     */
    public int authenticateUser(String login, String password) {
        // Выполнение запроса на поиск пользователя в таблице airport_user
        String userSql = "SELECT COUNT(*) FROM airport_user WHERE user_login = ? AND user_password = ?";
        int userCount = jdbcTemplate.queryForObject(userSql, new Object[]{login, password}, Integer.class);

        // Выполнение запроса на поиск администратора в таблице airport_admin
        String adminSql = "SELECT COUNT(*) FROM airport_admin WHERE admin_login = ? AND admin_password = ?";
        int adminCount = jdbcTemplate.queryForObject(adminSql, new Object[]{login, password}, Integer.class);

        // Проверка результатов запросов и возврат соответствующего кода
        if (userCount > 0) {
            // Получение идентификатора пользователя
            this.currentUserId = jdbcTemplate.queryForObject("SELECT user_id FROM airport_user WHERE user_login = ?", new Object[]{login}, Integer.class);
            return 1; // Пользователь
        } else if (adminCount > 0) {
            return 2; // Администратор
        } else {
            return 0; // Пользователь/Администратор не найден
        }
    }
}