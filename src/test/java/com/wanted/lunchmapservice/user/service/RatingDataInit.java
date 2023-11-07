package com.wanted.lunchmapservice.user.service;

import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.restaurant.repository.RestaurantRepository;
import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class RatingDataInit {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    private final Double testUserLat = 37.2738734428;
    private final Double testUserLon = 37.2738734428;
    private final List<Long> testRestaurants = List.of(8L, 1016L, 1045L, 1219L, 1455L, 1513L, 1817L, 1821L,
            2094L, 2365L, 2376L, 2820L, 2992L, 4458L, 4948L, 4951L, 5219L, 5520L, 6454L, 6559L);

    public RatingDataInit(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void createTestRatingData() {
        creatTestUser();
        createTestRating();
    }

    private void creatTestUser() {
        User user = User.builder()
                .userName("테스트")
                .password("1234")
                .longitude(testUserLon)
                .latitude(testUserLat)
                .build();
        userRepository.save(user);
    }

    private void createTestRating() {
        for (Long id : testRestaurants) {
            double score = Math.round(Math.random() * 100 * 10.0) / 10.0;
            Restaurant restaurant = restaurantRepository.findById(id).get();
            restaurant.setAverageScore(score);
        }
    }

}