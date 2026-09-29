package com.portfolio.petsittinghub.service;

import com.portfolio.petsittinghub.dto.request.CareTipRequest;
import com.portfolio.petsittinghub.dto.response.CareTipResponse;
import com.portfolio.petsittinghub.entity.CareTip;
import com.portfolio.petsittinghub.entity.User;
import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import com.portfolio.petsittinghub.exception.ResourceNotFoundException;
import com.portfolio.petsittinghub.exception.UnauthorizedException;
import com.portfolio.petsittinghub.repository.CareTipRepository;
import com.portfolio.petsittinghub.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CareTipService {

    private final CareTipRepository careTipRepository;
    private final UserRepository userRepository;

    public CareTipService(CareTipRepository careTipRepository, UserRepository userRepository) {
        this.careTipRepository = careTipRepository;
        this.userRepository = userRepository;
    }

    public List<CareTipResponse> findAll(AnimalType animalType, CareTipCategory category,
                                         Difficulty difficulty, String search) {
        List<CareTip> tips = careTipRepository.findByFilters(animalType, category, difficulty, search);
        return tips.stream().map(CareTipResponse::from).toList();
    }

    public CareTipResponse findById(Long id) {
        CareTip tip = careTipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Care tip not found with id: " + id));
        return CareTipResponse.from(tip);
    }

    @Transactional
    public CareTipResponse create(CareTipRequest request, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        CareTip tip = new CareTip();
        mapRequestToEntity(request, tip);
        tip.setAuthor(author);
        careTipRepository.save(tip);

        return CareTipResponse.from(tip);
    }

    @Transactional
    public CareTipResponse update(Long id, CareTipRequest request, String username) {
        CareTip tip = careTipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Care tip not found with id: " + id));

        if (!tip.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only edit your own tips");
        }

        mapRequestToEntity(request, tip);
        careTipRepository.save(tip);

        return CareTipResponse.from(tip);
    }

    @Transactional
    public void delete(Long id, String username) {
        CareTip tip = careTipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Care tip not found with id: " + id));

        if (!tip.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only delete your own tips");
        }

        careTipRepository.delete(tip);
    }

    private void mapRequestToEntity(CareTipRequest request, CareTip tip) {
        tip.setTitle(request.title());
        tip.setTitlePt(request.titlePt());
        tip.setContent(request.content());
        tip.setContentPt(request.contentPt());
        tip.setSummary(request.summary());
        tip.setSummaryPt(request.summaryPt());
        tip.setAnimalType(request.animalType());
        tip.setCategory(request.category());
        tip.setDifficulty(request.difficulty());
        tip.setImageUrl(request.imageUrl());
    }
}
