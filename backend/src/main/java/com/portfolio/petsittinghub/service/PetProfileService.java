package com.portfolio.petsittinghub.service;

import com.portfolio.petsittinghub.dto.request.PetProfileRequest;
import com.portfolio.petsittinghub.dto.response.PetProfileResponse;
import com.portfolio.petsittinghub.entity.PetProfile;
import com.portfolio.petsittinghub.entity.User;
import com.portfolio.petsittinghub.exception.ResourceNotFoundException;
import com.portfolio.petsittinghub.exception.UnauthorizedException;
import com.portfolio.petsittinghub.repository.PetProfileRepository;
import com.portfolio.petsittinghub.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final UserRepository userRepository;

    public PetProfileService(PetProfileRepository petProfileRepository, UserRepository userRepository) {
        this.petProfileRepository = petProfileRepository;
        this.userRepository = userRepository;
    }

    public List<PetProfileResponse> findByOwner(String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return petProfileRepository.findByOwnerId(owner.getId()).stream()
                .map(PetProfileResponse::from)
                .toList();
    }

    public PetProfileResponse findById(Long id) {
        PetProfile pet = petProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet profile not found with id: " + id));
        return PetProfileResponse.from(pet);
    }

    @Transactional
    public PetProfileResponse create(PetProfileRequest request, String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        PetProfile pet = new PetProfile();
        mapRequestToEntity(request, pet);
        pet.setOwner(owner);
        petProfileRepository.save(pet);

        return PetProfileResponse.from(pet);
    }

    @Transactional
    public PetProfileResponse update(Long id, PetProfileRequest request, String username) {
        PetProfile pet = petProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet profile not found with id: " + id));

        if (!pet.getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only edit your own pets");
        }

        mapRequestToEntity(request, pet);
        petProfileRepository.save(pet);

        return PetProfileResponse.from(pet);
    }

    @Transactional
    public void delete(Long id, String username) {
        PetProfile pet = petProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet profile not found with id: " + id));

        if (!pet.getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only delete your own pets");
        }

        petProfileRepository.delete(pet);
    }

    private void mapRequestToEntity(PetProfileRequest request, PetProfile pet) {
        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setAge(request.age());
        pet.setWeight(request.weight());
        pet.setDescription(request.description());
        pet.setImageUrl(request.imageUrl());
        pet.setAnimalType(request.animalType());
    }
}
