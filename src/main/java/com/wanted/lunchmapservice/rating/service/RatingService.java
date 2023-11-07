package com.wanted.lunchmapservice.rating.service;

import com.wanted.lunchmapservice.rating.dto.RatingCreateRequestDto;
import com.wanted.lunchmapservice.rating.entity.Rating;
import com.wanted.lunchmapservice.rating.repository.RatingRepository;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void createRating(RatingCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
            .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        
        Rating rating = Rating.builder()
            .score(requestDto.getScore())
            .content(requestDto.getContent())
            .user(user)
            .restaurant(restaurant)
            .build();
        
        ratingRepository.save(rating);
        
        restaurant.updateRating();
    }
}
