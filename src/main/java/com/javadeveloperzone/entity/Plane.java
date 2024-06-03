package com.javadeveloperzone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность "Самолет"
 * Представляет информацию о самолетах, используемых в аэропорту
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airport_plane")
public class Plane {
    /**
     * Идентификатор самолета
     */
    @Id
    private int planeId;

    /**
     * Название самолета
     */
    private String planeName;
}