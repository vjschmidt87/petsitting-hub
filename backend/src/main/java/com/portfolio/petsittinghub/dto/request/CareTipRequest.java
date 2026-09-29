package com.portfolio.petsittinghub.dto.request;

import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CareTipRequest(
        @NotBlank String title,
        @NotBlank String titlePt,
        @NotBlank String content,
        @NotBlank String contentPt,
        String summary,
        String summaryPt,
        @NotNull AnimalType animalType,
        @NotNull CareTipCategory category,
        @NotNull Difficulty difficulty,
        String imageUrl
) {}
