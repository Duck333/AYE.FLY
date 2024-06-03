package com.javadeveloperzone.controller;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.entity.User;
import com.javadeveloperzone.service.AuthService;
import com.javadeveloperzone.service.FlightService;
import com.javadeveloperzone.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final FlightService flightService;
    private final AuthService authService;

    /**
     * Конструирует новый экземпляр MainController с предоставленными сервисами.
     *
     * @param userService    экземпляр UserService для обработки операций, связанных с пользователями
     * @param flightService  экземпляр FlightService для обработки операций, связанных с рейсами
     * @param authService    экземпляр AuthService для обработки аутентификации и авторизации
     */
    public MainController(UserService userService, FlightService flightService, AuthService authService) {
        this.userService = userService;
        this.flightService = flightService;
        this.authService = authService;
    }

    /**
     * Обрабатывает запрос на главную страницу, отображая представление входа в систему.
     *
     * @return объект ModelAndView, содержащий представление входа в систему
     */
    @RequestMapping("")
    public ModelAndView enterController() {
        return new ModelAndView("mainHTML/logIn");
    }

    /**
     * Обрабатывает запрос на вход в систему, аутентифицируя пользователя и отображая соответствующее представление.
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @param model    модель Spring MVC
     * @return объект ModelAndView, содержащий представление пользователя или администратора, или представление входа в систему, если аутентификация не пройдена
     */
    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        int authResult = authService.authenticateUser(login, password);
        if (authResult == 1) {
            Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
            model.addAttribute("currentUserFlight", currentUserFlight);
            Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
            model.addAttribute("currentUser", currentUser);
            // Пользователь аутентифицирован
            return new ModelAndView("mainHTML/user");
        } else if (authResult == 2) {
            // Администратор аутентифицирован
            return new ModelAndView("mainHTML/admin");
        } else {
            // Аутентификация не пройдена
            return new ModelAndView("mainHTML/logIn");
        }
    }

    /**
     * Обрабатывает запрос на представление функциональности пользователя, отображая представление пользователя.
     *
     * @param model модель Spring MVC
     * @return объект ModelAndView, содержащий представление пользователя
     */
    @PostMapping("/userFunctional")
    public ModelAndView user(Model model) {
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);
        return new ModelAndView("mainHTML/user");
    }

    /**
     * Обрабатывает запрос на представление администратора, отображая представление администратора.
     *
     * @return объект ModelAndView, содержащий представление администратора
     */
    @PostMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("mainHTML/admin");
    }

    /**
     * Обрабатывает запрос на представление регистрации, отображая представление регистрации.
     *
     * @return объект ModelAndView, содержащий представление регистрации
     */
    @PostMapping("/singUp")
    public ModelAndView doCreatePlane() {
        return new ModelAndView("mainHTML/singUp");
    }

    /**
     * Обрабатывает запрос на регистрацию, создавая нового пользователя, если логин еще не занят.
     *
     * @param newUserRequest новый запрос пользователя
     * @param model          модель Spring MVC
     * @return объект ModelAndView, содержащий представление входа в систему или представление регистрации, если логин уже занят
     */
    @PostMapping("/doSingUp")
    public ModelAndView doSignUp(@ModelAttribute User newUserRequest, Model model) {
        // Проверяем, существует ли пользователь с таким же логином
        if (userService.getUserByLogin(newUserRequest.getUserLogin()).isPresent()) {
            // Если пользователь с таким логином уже существует, добавляем сообщение об ошибке в модель
            return new ModelAndView("mainHTML/singUp"); // Возвращаем пользователя на страницу регистрации
        }
        // Если пользователя с таким логином не существует, создаем нового пользователя
        User user = userService.createUser(newUserRequest);
        return new ModelAndView("mainHTML/logIn"); // Перенаправляем пользователя на страницу входа в систему
    }
}