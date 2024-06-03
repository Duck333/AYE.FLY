package com.javadeveloperzone.controller;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.entity.User;
import com.javadeveloperzone.service.AuthService;
import com.javadeveloperzone.service.FlightService;
import com.javadeveloperzone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final FlightService flightService;

    /**
     * Конструктор контроллера пользователей.
     *
     * @param userService    Сервис для работы с пользователями
     * @param authService    Сервис для аутентификации
     * @param flightService  Сервис для работы с рейсами
     */
    public UserController(UserService userService, AuthService authService, FlightService flightService) {
        this.userService = userService;
        this.authService = authService;
        this.flightService = flightService;
    }

    /**
     * Обработчик запроса для страницы управления пользователями.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-файла)
     */
    @RequestMapping("")
    public String userCRUD(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return "userHTML/userCRUD"; // Имя HTML-файла без расширения .html
    }

    /**
     * Обработчик запроса для страницы создания пользователя.
     *
     * @param model Модель для передачи данных в представление
     * @return Объект ModelAndView для перехода на страницу создания пользователя
     */
    @PostMapping("/createUser")
    public ModelAndView createUser(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/createUser");
    }

    /**
     * Обработчик запроса для создания нового пользователя.
     *
     * @param newUserRequest Объект пользователя с новыми данными
     * @param model          Модель для передачи данных в представление
     * @return Объект ModelAndView для перехода на страницу создания пользователя
     */
    @PostMapping("/doCreateUser")
    public ModelAndView doCreateUser(@ModelAttribute User newUserRequest, Model model) {
        // Проверяем, существует ли пользователь с таким же логином
        if (userService.getUserByLogin(newUserRequest.getUserLogin()).isPresent()) {
            // Получаем список всех пользователей
            List<User> listOfUsers = userService.getAllUsers();
            // Получаем список всех рейсов
            List<Flight> listOfFlights = flightService.getAllFlights();
            // Добавляем списки в модель
            model.addAttribute("users", listOfUsers);
            model.addAttribute("flights", listOfFlights);
            // Если пользователь с таким логином уже существует, добавляем сообщение об ошибке в модель
            return new ModelAndView("userHTML/createUser"); // Возвращаем пользователя на страницу регистрации
        }

        User user = userService.createUser(newUserRequest);
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/createUser");
    }

    /**
     * Обработчик запроса для страницы обновления имени пользователя по ID.
     *
     * @param model Модель для передачи данных в представление
     * @return Объект ModelAndView для перехода на страницу обновления имени пользователя
     */
    @PostMapping("/updateUserNameById")
    public ModelAndView updateUserNameById(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/updateUserNameById");
    }

    /**
     * Обработчик запроса для обновления имени пользователя по ID.
     *
     * @param userId      ID пользователя
     * @param newUserName Новое имя пользователя
     * @param model       Модель для передачи данных в представление
     * @return Имя представления (HTML-файла)
     */
    @PostMapping("/doUpdateUserNameById")
    public String doUpdateUserNameById(@RequestParam("userId") int userId, @RequestParam("userName") String newUserName, Model model) {
        userService.updateUserNameById(userId, newUserName);
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return "userHTML/updateUserNameById";
    }
    /**
     * Обновляет логин пользователя по его id.
     *
     * @param model Модель, используемая для передачи данных в представление.
     * @return ModelAndView с представлением, используемым для обновления логина пользователя.
     */
    @PostMapping("/updateUserLoginById")
    public ModelAndView updateUserLoginById(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/updateUserLoginById");
    }

    /**
     * Выполняет обновление логина пользователя по его id.
     *
     * @param userId      Идентификатор пользователя, логин которого необходимо обновить.
     * @param newUserLogin Новый логин пользователя.
     * @param model       Модель, используемая для передачи данных в представление.
     * @return String с названием представления, используемого для обновления логина пользователя.
     */
    @PostMapping("/doUpdateUserLoginById")
    public String doUpdateUserLoginById(@RequestParam("userId") int userId, @RequestParam("userLogin") String newUserLogin, Model model) {
        userService.updateUserLoginById(userId, newUserLogin);
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return "userHTML/updateUserLoginById";
    }

    /**
     * Открывает страницу для обновления пароля пользователя по его id.
     *
     * @param model Модель, используемая для передачи данных в представление.
     * @return ModelAndView с представлением, используемым для обновления пароля пользователя.
     */
    @PostMapping("/updateUserPasswordById")
    public ModelAndView updateFlightPassword(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/updateUserPasswordById");
    }

    /**
     * Обновляет пароль пользователя по его id.
     *
     * @param userId           Идентификатор пользователя, пароль которого необходимо обновить.
     * @param newUserPassword Новый пароль пользователя.
     * @param model           Модель, используемая для передачи данных в представление.
     * @return String с названием представления, используемого для обновления пароля пользователя.
     */
    @PostMapping("/doUpdateUserPasswordById")
    public String doUpdateUserPassword(@RequestParam("userId") int userId, @RequestParam("userPassword") String newUserPassword, Model model) {
        userService.updateUserPasswordById(userId, newUserPassword);
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return "userHTML/updateUserPasswordById";
    }

    /**
     * Открывает страницу для удаления пользователя по его id.
     *
     * @param model Модель, используемая для передачи данных в представление.
     * @return ModelAndView с представлением, используемым для удаления пользователя.
     */
    @PostMapping("/deleteUserById")
    public ModelAndView deleteUserById(Model model) {
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/deleteUserById");
    }

    /**
     * Выполняет удаление пользователя по его id.
     *
     * @param flightId Идентификатор пользователя, который необходимо удалить.
     * @param model    Модель, используемая для передачи данных в представление.
     * @return String с названием представления, используемого для удаления пользователя.
     */
    @PostMapping("/doDeleteUserById")
    public String doDeleteFlightById(@RequestParam("userId") int flightId, Model model) {
        userService.deleteUserById(flightId);
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return "userHTML/deleteUserById";
    }

    /**
     * Выполняет удаление всех пользователей.
     *
     * @param model Модель, используемая для передачи данных в представление.
     * @return ModelAndView с представлением, используемым для отображения CRUD-операций с пользователями.
     */
    @PostMapping("/doDeleteAllUsers")
    @ResponseBody
    public ModelAndView deleteAllUsers(Model model) {
        flightService.deleteAllFlightsWithUser();
        userService.deleteAllUsers();
        // Получаем список всех пользователей
        List<User> listOfUsers = userService.getAllUsers();
        // Получаем список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Добавляем списки в модель
        model.addAttribute("users", listOfUsers);
        model.addAttribute("flights", listOfFlights);
        return new ModelAndView("userHTML/userCRUD");
    }

    /**
     * Контроллер обновления имени пользователя.
     *
     * @param model модель данных для представления
     * @return представление для страницы обновления имени пользователя
     */
    @PostMapping("/updateUserName")
    public ModelAndView updateUserName(Model model) {
        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для страницы обновления имени пользователя
        return new ModelAndView("userHTML/updateUserName");
    }

    /**
     * Контроллер выполнения обновления имени пользователя.
     *
     * @param newUserName новое имя пользователя
     * @param model модель данных для представления
     * @return представление для главной страницы пользователя
     */
    @PostMapping("/doUpdateUserName")
    public String doUpdateUserName(@RequestParam("userName") String newUserName, Model model) {
        // Обновление имени пользователя в базе данных
        userService.updateUserNameById(authService.getCurrentUserId(), newUserName);

        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для главной страницы пользователя
        return "mainHTML/user";
    }

    /**
     * Контроллер обновления логина пользователя.
     *
     * @param model модель данных для представления
     * @return представление для страницы обновления логина пользователя
     */
    @PostMapping("/updateUserLogin")
    public ModelAndView updateUserLogin(Model model) {
        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для страницы обновления логина пользователя
        return new ModelAndView("userHTML/updateUserLogin");
    }

    /**
     * Контроллер выполнения обновления логина пользователя.
     *
     * @param newUserLogin новый логин пользователя
     * @param model модель данных для представления
     * @return представление для главной страницы пользователя
     */
    @PostMapping("/doUpdateUserLogin")
    public String doUpdateUserLogin(@RequestParam("userLogin") String newUserLogin, Model model) {
        // Обновление логина пользователя в базе данных
        userService.updateUserLoginById(authService.getCurrentUserId(), newUserLogin);

        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для главной страницы пользователя
        return "mainHTML/user";
    }

    /**
     * Контроллер обновления пароля пользователя.
     *
     * @param model модель данных для представления
     * @return представление для страницы обновления пароля пользователя
     */
    @PostMapping("/updateUserPassword")
    public ModelAndView updateUserPassword(Model model) {
        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для страницы обновления пароля пользователя
        return new ModelAndView("userHTML/updateUserPassword");
    }

    /**
     * Контроллер выполнения обновления пароля пользователя.
     *
     * @param newUserPassword новый пароль пользователя
     * @param model модель данных для представления
     * @return представление для главной страницы пользователя
     */
    @PostMapping("/doUpdateUserPassword")
    public String doUpdateUserPassword(@RequestParam("userPassword") String newUserPassword, Model model) {
        // Обновление пароля пользователя в базе данных
        userService.updateUserPasswordById(authService.getCurrentUserId(), newUserPassword);

        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для главной страницы пользователя
        return "mainHTML/user";
    }

    /**
     * Контроллер удаления учетной записи пользователя.
     *
     * @param model модель данных для представления
     * @return представление для страницы входа в систему
     */
    @PostMapping("/doDeleteAccount")
    public String doDeleteUser(Model model) {
        // Удаление полета пользователя из базы данных
        flightService.deleteFlightByUserId(authService.getCurrentUserId());

        // Получение данных о текущем полете пользователя
        Optional<Flight> currentUserFlight = flightService.getFlightByUserId(authService.getCurrentUserId());
        model.addAttribute("currentUserFlight", currentUserFlight);

        // Получение данных о текущем пользователе
        Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
        model.addAttribute("currentUser", currentUser);

        // Возвращение представления для страницы входа в систему
        return "mainHTML/logIn";
    }
}