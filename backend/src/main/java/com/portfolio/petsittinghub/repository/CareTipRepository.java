package com.portfolio.petsittinghub.repository;

import com.portfolio.petsittinghub.entity.CareTip;
import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CareTipRepository extends JpaRepository<CareTip, Long> {

    List<CareTip> findByAnimalType(AnimalType animalType);

    List<CareTip> findByCategory(CareTipCategory category);

    List<CareTip> findByDifficulty(Difficulty difficulty);

    @Query("SELECT t FROM CareTip t WHERE " +
           "(:animalType IS NULL OR t.animalType = :animalType) AND " +
           "(:category IS NULL OR t.category = :category) AND " +
           "(:difficulty IS NULL OR t.difficulty = :difficulty) AND " +
           "(:search IS NULL OR LOWER(CAST(t.title AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) " +
           "OR LOWER(CAST(t.content AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) " +
           "OR LOWER(CAST(t.titlePt AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) " +
           "OR LOWER(CAST(t.contentPt AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))")
    List<CareTip> findByFilters(
            @Param("animalType") AnimalType animalType,
            @Param("category") CareTipCategory category,
            @Param("difficulty") Difficulty difficulty,
            @Param("search") String search);
}
