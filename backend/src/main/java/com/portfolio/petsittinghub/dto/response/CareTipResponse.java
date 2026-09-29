package com.portfolio.petsittinghub.dto.response;

import com.portfolio.petsittinghub.entity.CareTip;
import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;

import java.time.LocalDateTime;

public record CareTipResponse(
        Long id,
        String title,
        String titlePt,
        String content,
        String contentPt,
        String summary,
        String summaryPt,
        AnimalType animalType,
        CareTipCategory category,
        Difficulty difficulty,
        String imageUrl,
        String authorUsername,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CareTipResponse from(CareTip tip) {
        return new CareTipResponse(
                tip.getId(),
                tip.getTitle(),
                tip.getTitlePt(),
                tip.getContent(),
                tip.getContentPt(),
                tip.getSummary(),
                tip.getSummaryPt(),
                tip.getAnimalType(),
                tip.getCategory(),
                tip.getDifficulty(),
                tip.getImageUrl(),
                tip.getAuthor().getUsername(),
                tip.getCreatedAt(),
                tip.getUpdatedAt()
        );
    }
}
