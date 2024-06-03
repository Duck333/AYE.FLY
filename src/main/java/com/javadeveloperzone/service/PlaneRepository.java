package com.javadeveloperzone.service;

import com.javadeveloperzone.entity.Plane;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория для работы с сущностью "Самолет".
 * Этот интерфейс наследует от {@link CrudRepository} и содержит
 * методы для управления самолетами.
 */
@Repository
public interface PlaneRepository extends CrudRepository<Plane, Integer> {
    /**
     * Обновляет название самолета с указанным идентификатором.
     *
     * @param planeId        идентификатор самолета
     * @param newPlaneName новое название самолета
     */
    @Modifying
    @Transactional
    @Query("UPDATE Plane plane SET plane.planeName = :newPlaneName WHERE plane.planeId = :planeId")
    void updatePlaneNameById(@Param("planeId") int planeId, @Param("newPlaneName") String newPlaneName);
}
