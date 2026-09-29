package com.portfolio.petsittinghub.controller;

import com.portfolio.petsittinghub.dto.request.CareTipRequest;
import com.portfolio.petsittinghub.dto.response.CareTipResponse;
import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import com.portfolio.petsittinghub.service.CareTipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
public class CareTipController {

    private final CareTipService careTipService;

    public CareTipController(CareTipService careTipService) {
        this.careTipService = careTipService;
    }

    @GetMapping
    public ResponseEntity<List<CareTipResponse>> getAll(
            @RequestParam(required = false) AnimalType animalType,
            @RequestParam(required = false) CareTipCategory category,
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(careTipService.findAll(animalType, category, difficulty, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareTipResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(careTipService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CareTipResponse> create(
            @Valid @RequestBody CareTipRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(careTipService.create(request, userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareTipResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CareTipRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(careTipService.update(id, request, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        careTipService.delete(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
