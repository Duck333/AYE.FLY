package com.javadeveloperzone.service;

import com.javadeveloperzone.entity.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный класс для управления самолетами.
 * Этот класс предоставляет методы для получения, создания, обновления и удаления самолетов.
 */
@Service
public class PlaneService {
    private static PlaneRepository planeRepository;

    /**
     * Конструктор, инициализирующий репозиторий самолетов.
     *
     * @param planeRepository репозиторий для работы с сущностью "Самолет"
     */
    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        PlaneService.planeRepository = planeRepository;
    }

    /**
     * Возвращает список всех самолетов.
     *
     * @return список всех самолетов
     */
    public static List<Plane> getAllPlanes() {
        return (List<Plane>) planeRepository.findAll();
    }

    /**
     * Удаляет самолет с указанным идентификатором.
     *
     * @param planeId идентификатор самолета, который нужно удалить
     */
    public void deletePlaneById(int planeId) {
        planeRepository.deleteById(planeId);
    }

    /**
     * Создает новый самолет.
     *
     * @param plane объект "Самолет", который нужно создать
     */
    public void createPlane(Plane plane) {
        planeRepository.save(plane);
    }

    /**
     * Удаляет все самолеты.
     */
    public void deleteAllPlanes() {
        planeRepository.deleteAll();
    }

    /**
     * Обновляет название самолета с указанным идентификатором.
     *
     * @param planeId        идентификатор самолета
     * @param newPlaneName новое название самолета
     */
    public void updatePlaneNameById(int planeId, String newPlaneName) {
        planeRepository.updatePlaneNameById(planeId, newPlaneName);
    }
}