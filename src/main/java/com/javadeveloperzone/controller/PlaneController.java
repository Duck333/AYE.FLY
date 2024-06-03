package com.javadeveloperzone.controller;

import com.javadeveloperzone.entity.Plane;
import com.javadeveloperzone.service.PlaneService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    /**
     * Конструктор для создания объекта PlaneController.
     *
     * @param planeService Сервис для работы с самолётами
     */
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    /**
     * Обрабатывает запрос на отображение страницы управления самолётами.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @RequestMapping("")
    public String planeCRUD(Model model) {
        // Получаем список всех самолётов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавляем список самолётов в модель
        model.addAttribute("planes", listOfPlanes);
        return "planeHTML/planeCRUD"; // Имя вашего HTML-файла без расширения .html
    }

    /**
     * Обрабатывает запрос на отображение страницы создания нового самолёта.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @PostMapping("/createPlane")
    public ModelAndView createPlane(Model model) {
        // Получаем список всех самолётов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавляем список самолётов в модель
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("planeHTML/createPlane");
    }

    /**
     * Обрабатывает запрос на создание нового самолёта.
     *
     * @param plane Объект самолёта с данными для создания
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения списка самолётов
     */
    @PostMapping("/doCreatePlane")
    public String doCreatePlane(@ModelAttribute Plane plane, Model model) {
        try {
            // Создаем новый самолёт на основе полученных данных
            planeService.createPlane(plane);
        } catch (Exception e) {
            // Получаем список всех самолётов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавляем список самолётов в модель
            model.addAttribute("planes", listOfPlanes);
            // Возвращаем имя представления (HTML шаблона) для отображения списка самолётов
            return "planeHTML/createPlane";
        }

        // Получаем список всех самолётов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавляем список самолётов в модель
        model.addAttribute("planes", listOfPlanes);
        // Возвращаем имя представления (HTML шаблона) для отображения списка самолётов
        return "planeHTML/createPlane";
    }

    /**
     * Обрабатывает запрос на отображение страницы удаления самолёта по ID.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @PostMapping("/deletePlaneById")
    @ResponseBody
    public ModelAndView deletePlaneById(Model model) {
        // Получаем список всех самолётов
        List<Plane> listOfPlanes = planeService.getAllPlanes();
        // Добавляем список самолётов в модель
        model.addAttribute("planes", listOfPlanes);
        return new ModelAndView("planeHTML/deletePlaneById");
    }

    /**
     * Обрабатывает запрос на удаление самолёта по ID.
     *
     * @param planeId ID самолёта для удаления
     * @param model   Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @PostMapping("/doDeletePlaneById")
    public String doDeletePlaneById(@RequestParam("planeId") int planeId, Model model) {
        try {
            planeService.deletePlaneById(planeId);
            // Получаем список всех самолётов
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            // Добавляем список самолётов в модель
            model.addAttribute("planes", listOfPlanes);
            return "planeHTML/deletePlaneById"; // Имя вашего HTML-файла без расширения .html
        } catch (DataIntegrityViolationException ex) {
            // Если возникает ошибка DataIntegrityViolationException, значит самолёт связан с записями в базе данных
            model.addAttribute("errorMessage", "Этот самолёт не может быть удален, так как он связан с другими записями в базе данных.");
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            return "planeHTML/deletePlaneById";
        } catch (Exception ex) {
            // Обработка других исключений
            model.addAttribute("errorMessage", "Произошла ошибка при удалении самолёта: " + ex.getMessage());
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            return "planeHTML/deletePlaneById";
        }
    }

    /**
     * Обрабатывает запрос на удаление всех самолётов.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @PostMapping("/doDeleteAllPlanes")
    @ResponseBody
    public ModelAndView doDeleteAllPlanes(Model model) {
        try {
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            planeService.deleteAllPlanes();
            return new ModelAndView("planeHTML/planeCRUD");
        } catch (DataIntegrityViolationException ex) {
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            return new ModelAndView("planeHTML/planeCRUD");
        }
    }

    /**
     * Обновление названия самолёта по его идентификатору.
     *
     * @param model Объект модели, используемый для передачи данных в представление.
     * @return Объект ModelAndView, содержащий название представления, которое должно быть отображено.
     */
    @PostMapping("/updatePlaneNameById")
    @ResponseBody
    public ModelAndView updatePlaneNameById(Model model) {
        // Получаем список всех самолётов
        List<Plane> listOfPlanes = planeService.getAllPlanes();

        // Добавляем список самолётов в модель
        model.addAttribute("planes", listOfPlanes);

        // Возвращаем объект ModelAndView с названием представления, которое должно быть отображено
        return new ModelAndView("planeHTML/updatePlaneNameById");
    }

    /**
     * Обрабатывает запрос на отображение страницы обновления названия самолёта.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления (HTML-шаблона) для отображения страницы
     */
    @PostMapping("/doUpdatePlaneNameById")
    @ResponseBody
    public ModelAndView doUpdatePlaneNameById(@RequestParam("planeId") int planeId, @RequestParam("planeName") String newPlaneName, Model model) {
        try {
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            planeService.updatePlaneNameById(planeId, newPlaneName);
            return new ModelAndView("planeHTML/updatePlaneNameById");
        } catch (Exception ex) {
            List<Plane> listOfPlanes = planeService.getAllPlanes();
            model.addAttribute("planes", listOfPlanes);
            return new ModelAndView("planeHTML/updatePlaneNameById");
        }
    }
}