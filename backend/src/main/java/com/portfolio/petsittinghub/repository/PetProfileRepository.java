package com.portfolio.petsittinghub.repository;

import com.portfolio.petsittinghub.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {
    List<PetProfile> findByOwnerId(Long ownerId);
}
