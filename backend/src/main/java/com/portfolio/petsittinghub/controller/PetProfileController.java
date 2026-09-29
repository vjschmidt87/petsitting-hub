package com.portfolio.petsittinghub.controller;

import com.portfolio.petsittinghub.dto.request.PetProfileRequest;
import com.portfolio.petsittinghub.dto.response.PetProfileResponse;
import com.portfolio.petsittinghub.service.PetProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetProfileController {

    private final PetProfileService petProfileService;

    public PetProfileController(PetProfileService petProfileService) {
        this.petProfileService = petProfileService;
    }

    @GetMapping
    public ResponseEntity<List<PetProfileResponse>> getMyPets(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(petProfileService.findByOwner(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetProfileResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(petProfileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PetProfileResponse> create(
            @Valid @RequestBody PetProfileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petProfileService.create(request, userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetProfileResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PetProfileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(petProfileService.update(id, request, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        petProfileService.delete(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
