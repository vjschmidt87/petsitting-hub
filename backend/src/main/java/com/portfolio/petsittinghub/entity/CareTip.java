package com.portfolio.petsittinghub.entity;

import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "care_tips")
public class CareTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "title_pt", nullable = false, length = 200)
    private String titlePt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "content_pt", nullable = false, columnDefinition = "TEXT")
    private String contentPt;

    @Column(length = 500)
    private String summary;

    @Column(name = "summary_pt", length = 500)
    private String summaryPt;

    @Enumerated(EnumType.STRING)
    @Column(name = "animal_type", nullable = false, length = 20)
    private AnimalType animalType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CareTipCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Difficulty difficulty;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTitlePt() { return titlePt; }
    public void setTitlePt(String titlePt) { this.titlePt = titlePt; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getContentPt() { return contentPt; }
    public void setContentPt(String contentPt) { this.contentPt = contentPt; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getSummaryPt() { return summaryPt; }
    public void setSummaryPt(String summaryPt) { this.summaryPt = summaryPt; }

    public AnimalType getAnimalType() { return animalType; }
    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }

    public CareTipCategory getCategory() { return category; }
    public void setCategory(CareTipCategory category) { this.category = category; }

    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
