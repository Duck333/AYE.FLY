package com.javadeveloperzone.controller;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.entity.Plane;
import com.javadeveloperzone.entity.User;
import com.javadeveloperzone.service.AuthService;
import com.javadeveloperzone.service.PlaneService;
import com.javadeveloperzone.service.FlightService;
import com.javadeveloperzone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final UserService userService;
    private final FlightService flightService;
    private final PlaneService planeService;
    private final AuthService authService;

    /**
     * Конструктор FlightController, который инициализирует все необходимые сервисы.
     *
     * @param userService    сервис для работы с пользователями
     * @param flightService  сервис для работы с рейсами
     * @param planeService   сервис для работы с самолетами
     * @param authService    сервис для аутентификации
     */
    public FlightController(UserService userService, FlightService flightService, PlaneService planeService, AuthService authService) {
        this.userService = userService;
        this.flightService = flightService;
        this.planeService = planeService;
        this.authService = authService;
    }

    /**
     * Обработчик для URL-адреса "/flight", который возвращает страницу CRUD для рейсов.
     * Метод получает список всех рейсов и самолетов, и добавляет их в модель.
     *
     * @param model объект модели для передачи данных в представление
     * @return имя представления, которое должно быть отображено
     */
    @RequestMapping("")
    public String flightCRUD(Model model) {
        // Получите список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получите список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавьте оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return "flightHTML/flightCRUD"; // Имя вашего HTML-файла без расширения .html
    }

    /**
     * Обработчик для URL-адреса "/flight/createFlight", который возвращает страницу для создания нового рейса.
     * Метод получает список всех рейсов и самолетов, и добавляет их в модель.
     *
     * @param model объект модели для передачи данных в представление
     * @return объект ModelAndView, содержащий имя представления, которое должно быть отображено
     */
    @PostMapping("/createFlight")
    public ModelAndView createFlight(Model model) {
        // Получите список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получите список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавьте оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/createFlight");
    }

    /**
     * Обработчик для URL-адреса "/flight/doCreateFlight", который создает новый рейс.
     * Метод получает данные нового рейса из запроса, создает новый рейс и сохраняет его.
     * Если возникает ошибка, метод возвращает страницу для создания рейса с обновленными списками рейсов и самолетов.
     *
     * @param flight объект Flight, содержащий данные о новом рейсе
     * @param model  объект модели для передачи данных в представление
     * @return объект ModelAndView, содержащий имя представления, которое должно быть отображено
     */
    @PostMapping("/doCreateFlight")
    public ModelAndView doCreateFlight(@ModelAttribute Flight flight, Model model) {
        try {
            // Создаем новый рейс на основе полученных данных
            flightService.createFlight(flight);
        } catch (Exception e) {
            // Получите обновленный список всех рейсов
            List<Flight> listOfFlights = flightService.getAllFlights();
            // Получите список всех самолетов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавьте оба списка в модель
            model.addAttribute("flights", listOfFlights);
            model.addAttribute("planes", listOfPlanes);

            return new ModelAndView("flightHTML/createFlight");
        }
        // Получите обновленный список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получите список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавьте оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/createFlight");
    }

    /**
     * Обновляет название рейса по заданному идентификатору рейса.
     *
     * @param model объект модели Spring MVC
     * @return имя представления для страницы обновления названия рейса
     */
    @PostMapping("/updateFlightNameById")
    public ModelAndView updateFlightName(Model model) {
        // Получите список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получите список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавьте оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/updateFlightNameById");
    }

    /**
     * Выполняет обновление названия рейса по заданному идентификатору рейса.
     *
     * @param flightId      идентификатор рейса для обновления
     * @param newFlightName новое название рейса, которое нужно установить
     * @param model         объект модели Spring MVC
     * @return имя представления для страницы обновления названия рейса
     */
    @PostMapping("/doUpdateFlightNameById")
    public String updateFlightName(@RequestParam("flightId") int flightId, @RequestParam("flightName") String newFlightName, Model model) {
        try {
            // Попытайтесь обновить название рейса
            flightService.updateFlightNameByFlightId(flightId, newFlightName);
            // Получите список всех рейсов
            List<Flight> listOfFlights = flightService.getAllFlights();
            // Получите список всех самолетов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавьте оба списка в модель
            model.addAttribute("flights", listOfFlights);
            model.addAttribute("planes", listOfPlanes);
            return "flightHTML/updateFlightNameById";
        } catch (Exception e) {
            return "flightHTML/updateFlightNameById";
        }
    }

    /**
     * Обновляет идентификатор самолета для заданного рейса.
     *
     * @param model объект модели Spring MVC
     * @return имя представления для страницы обновления идентификатора самолета рейса
     */
    @PostMapping("/updatePlaneIdById")
    public ModelAndView updatePlaneId(Model model) {
        // Получите список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получите список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавьте оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/updateFlightPlaneIdById");
    }

    /**
     * Выполняет обновление идентификатора самолета для заданного рейса.
     *
     * @param flightId    идентификатор рейса для обновления
     * @param newPlaneId  новый идентификатор самолета, который нужно установить
     * @param model       объект модели Spring MVC
     * @return имя представления для страницы обновления идентификатора самолета рейса
     */
    @PostMapping("/doUpdateFlightPlaneIdById")
    public String updateFlightPlaneId(@RequestParam("flightId") int flightId, @RequestParam("planeId") int newPlaneId, Model model) {
        try {
            // Попытайтесь обновить идентификатор самолета рейса
            flightService.updateFlightPlaneIdByFlightId(flightId, newPlaneId);
            // Получите список всех рейсов
            List<Flight> listOfFlights = flightService.getAllFlights();
            // Получите список всех самолетов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавьте оба списка в модель
            model.addAttribute("flights", listOfFlights);
            model.addAttribute("planes", listOfPlanes);
            return "flightHTML/updateFlightPlaneIdById";
        } catch (Exception e) {
            return "flightHTML/updateFlightPlaneIdById";
        }
    }
    /**
     * Обрабатывает удаление рейса по его идентификатору.
     *
     * @param model объект модели для добавления данных
     * @return имя представления для страницы удаления рейса
     */
    @PostMapping("/deleteFlightById")
    public ModelAndView deleteFlightById(Model model) {
        // Получить список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получить список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавить оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/deleteFlightById");
    }

    /**
     * Обрабатывает фактическое удаление рейса по его идентификатору.
     *
     * @param flightId идентификатор рейса для удаления
     * @param model    объект модели для добавления данных
     * @return имя представления для страницы удаления рейса
     */
    @PostMapping("/doDeleteFlightById")
    public String deleteFlightById(@RequestParam("flightId") int flightId, Model model) {
        try {
            // Попытка удалить рейс
            flightService.deleteFlightById(flightId);
            // Получить список всех рейсов
            List<Flight> listOfFlights = flightService.getAllFlights();
            // Получить список всех самолетов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавить оба списка в модель
            model.addAttribute("flights", listOfFlights);
            model.addAttribute("planes", listOfPlanes);

            return "flightHTML/deleteFlightById";
        } catch (Exception e) {
            return "flightHTML/deleteFlightById";
        }
    }

    /**
     * Обрабатывает удаление всех рейсов.
     *
     * @param model объект модели для добавления данных
     * @return имя представления для страницы управления рейсами
     */
    @PostMapping("/doDeleteAllFlights")
    public ModelAndView deleteAllFlights(Model model) {
        try {
            // Попытка удалить все рейсы
            flightService.deleteAllFlights();
            // Перенаправить пользователя на страницу управления рейсами
            return new ModelAndView("flightHTML/flightCRUD");
        } catch (Exception e) {
            return new ModelAndView("flightHTML/flightCRUD");
        }
    }

    /**
     * Обрабатывает отображение страницы выбора рейса.
     *
     * @param model объект модели для добавления данных
     * @return имя представления для страницы выбора рейса
     */
    @PostMapping("/chooseFlight")
    public ModelAndView chooseFlight(Model model) {
        // Получить список всех рейсов
        List<Flight> listOfFlights = flightService.getAllFlights();
        // Получить список всех самолетов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавить оба списка в модель
        model.addAttribute("flights", listOfFlights);
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("flightHTML/chooseFlight");
    }

    /**
     * Обрабатывает фактический выбор рейса для текущего пользователя.
     *
     * @param flightId идентификатор рейса для выбора
     * @param model    объект модели для добавления данных
     * @return имя представления для страницы пользователя
     */
    @PostMapping("/doChooseFlight")
    public String chooseFlight(@RequestParam("flightId") int flightId, Model model) {
        try {
            // Попытка выбрать рейс для текущего пользователя
            flightService.chooseFlight(authService.getCurrentUserId(), flightId);
            // Получить текущего пользователя
            Optional<User> currentUser = userService.getUserById(authService.getCurrentUserId());
            // Добавить текущего пользователя в модель
            model.addAttribute("currentUser", currentUser);
            // Перенаправить пользователя на страницу пользователя
            return "mainHTML/user";
        } catch (Exception e) {
            return "mainHTML/user";
        }
    }
}
