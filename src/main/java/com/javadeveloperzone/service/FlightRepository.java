package com.javadeveloperzone.service;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью "Рейс" в базе данных.
 * Этот интерфейс расширяет интерфейс {@link CrudRepository} для операций CRUD над сущностью "Рейс".
 * Он также предоставляет дополнительные методы, специфичные для управления рейсами.
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {
    /**
     * Удаляет все рейсы, связанные с указанным пользователем.
     *
     * @param userId идентификатор пользователя, рейсы которого необходимо удалить
     */
    @Transactional
    void deleteFlightByUserId(int userId);

    /**
     * Возвращает опциональный объект рейса, связанный с указанным пользователем.
     *
     * @param userId идентификатор пользователя
     * @return опциональный объект рейса, связанный с указанным пользователем
     */
    Optional<Flight> getFlightByUserId(int userId);

    /**
     * Удаляет все рейсы, для которых задан идентификатор пользователя.
     */
    @Modifying
    @Transactional
    @Query("DELETE Flight flight WHERE flight.userId != null")
    void deleteAllFlightsWithUsers();

    /**
     * Обновляет название рейса с указанным идентификатором.
     *
     * @param flightId        идентификатор рейса
     * @param newFlightName новое название рейса
     */
    @Modifying
    @Transactional
    @Query("UPDATE Flight flight SET flight.flightName = :newFlightName WHERE flight.flightId = :flightId")
    void updateFlightNameByFlightId(@Param("flightId") int flightId, @Param("newFlightName") String newFlightName);

    /**
     * Обновляет идентификатор самолета для рейса с указанным идентификатором.
     *
     * @param flightId               идентификатор рейса
     * @param newFlightPlaneId новый идентификатор самолета
     */
    @Modifying
    @Transactional
    @Query("UPDATE Flight flight SET flight.planeId = :newFlightPlaneId WHERE flight.flightId = :flightId")
    void updateFlightPlaneIdByFlightId(@Param("flightId") int flightId, @Param("newFlightPlaneId") int newFlightPlaneId);

    /**
     * Назначает рейс с указанным идентификатором пользователю с указанным идентификатором.
     *
     * @param userId    идентификатор пользователя
     * @param flightId идентификатор рейса
     */
    @Modifying
    @Transactional
    @Query("UPDATE Flight flight SET flight.userId = :userId WHERE flight.flightId = :flightId")
    void chooseFlight(@Param("userId") int userId, @Param("flightId") int flightId);
}