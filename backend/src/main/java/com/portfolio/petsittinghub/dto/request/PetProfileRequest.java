package com.portfolio.petsittinghub.dto.request;

import com.portfolio.petsittinghub.enums.AnimalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetProfileRequest(
        @NotBlank String name,
        @NotBlank String species,
        String breed,
        Integer age,
        Double weight,
        String description,
        String imageUrl,
        @NotNull AnimalType animalType
) {}
