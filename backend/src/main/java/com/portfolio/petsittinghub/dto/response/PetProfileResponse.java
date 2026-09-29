package com.portfolio.petsittinghub.dto.response;

import com.portfolio.petsittinghub.entity.PetProfile;
import com.portfolio.petsittinghub.enums.AnimalType;

import java.time.LocalDateTime;

public record PetProfileResponse(
        Long id,
        String name,
        String species,
        String breed,
        Integer age,
        Double weight,
        String description,
        String imageUrl,
        AnimalType animalType,
        String ownerUsername,
        LocalDateTime createdAt
) {
    public static PetProfileResponse from(PetProfile pet) {
        return new PetProfileResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getAge(),
                pet.getWeight(),
                pet.getDescription(),
                pet.getImageUrl(),
                pet.getAnimalType(),
                pet.getOwner().getUsername(),
                pet.getCreatedAt()
        );
    }
}
