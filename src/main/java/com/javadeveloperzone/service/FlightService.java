package com.javadeveloperzone.service;

import com.javadeveloperzone.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для работы с сущностью "Рейс".
 * Этот класс предоставляет методы для управления рейсами, используя репозиторий {@link FlightRepository}.
 */
@Service
public class FlightService {
    private static FlightRepository flightRepository;

    /**
     * Конструктор, который инжектирует репозиторий {@link FlightRepository} через конструктор.
     *
     * @param flightRepository репозиторий для работы с сущностью "Рейс"
     */
    @Autowired
    public FlightService(FlightRepository flightRepository) {
        FlightService.flightRepository = flightRepository;
    }

    /**
     * Возвращает список всех рейсов.
     *
     * @return список всех рейсов
     */
    public static List<Flight> getAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    /**
     * Удаляет рейс с указанным идентификатором.
     *
     * @param flightId идентификатор рейса, который необходимо удалить
     */
    public void deleteFlightById(int flightId) {
        flightRepository.deleteById(flightId);
    }

    /**
     * Удаляет все рейсы, связанные с указанным пользователем.
     *
     * @param userId идентификатор пользователя, рейсы которого необходимо удалить
     */
    public void deleteFlightByUserId(int userId) {
        flightRepository.deleteFlightByUserId(userId);
    }

    /**
     * Обновляет название рейса с указанным идентификатором.
     *
     * @param flightId        идентификатор рейса
     * @param newFlightName новое название рейса
     */
    public void updateFlightNameByFlightId(int flightId, String newFlightName) {
        flightRepository.updateFlightNameByFlightId(flightId, newFlightName);
    }

    /**
     * Обновляет идентификатор самолета для рейса с указанным идентификатором.
     *
     * @param flightId               идентификатор рейса
     * @param newFlightPlaneId новый идентификатор самолета
     */
    public void updateFlightPlaneIdByFlightId(int flightId, int newFlightPlaneId) {
        flightRepository.updateFlightPlaneIdByFlightId(flightId, newFlightPlaneId);
    }

    /**
     * Удаляет все рейсы.
     */
    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

    /**
     * Удаляет все рейсы, для которых задан идентификатор пользователя.
     */
    public void deleteAllFlightsWithUser() {
        flightRepository.deleteAllFlightsWithUsers();
    }

    /**
     * Назначает рейс с указанным идентификатором пользователю с указанным идентификатором.
     *
     * @param userId    идентификатор пользователя
     * @param flightId идентификатор рейса
     */
    public void chooseFlight(int userId, int flightId) {
        flightRepository.chooseFlight(userId, flightId);
    }

    /**
     * Возвращает опциональный объект рейса, связанный с указанным пользователем.
     *
     * @param userId идентификатор пользователя
     * @return опциональный объект рейса, связанный с указанным пользователем
     */
    public Optional<Flight> getFlightByUserId(int userId) {
        return flightRepository.getFlightByUserId(userId);
    }

    /**
     * Создает новый рейс.
     *
     * @param flight объект рейса, который необходимо сохранить
     */
    public void createFlight(Flight flight) {
        flightRepository.save(flight);
    }
}