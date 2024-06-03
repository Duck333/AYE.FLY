package com.javadeveloperzone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность "Полет"
 * Представляет информацию о рейсах, выполняемых в аэропорту
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airport_flight")
public class Flight {
    /**
     * Идентификатор полета
     */
    @Id
    private int flightId;

    /**
     * Название полета
     */
    private String flightName;

    /**
     * Идентификатор самолета, выполняющего данный полет
     */
    private int planeId;

    /**
     * Идентификатор пользователя, забронировавшего место на данном рейсе
     * Может быть null, если место не забронировано
     */
    @Column(nullable = true)
    private Integer userId;
}