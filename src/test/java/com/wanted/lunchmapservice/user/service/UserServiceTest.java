package com.wanted.lunchmapservice.user.service;

import com.wanted.lunchmapservice.restaurant.dto.response.RestaurantResponseDto;
import com.wanted.lunchmapservice.user.dto.request.UserRestaurantRequestDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingDataInit ratingDataInit;
    private final int EARTH_RADIUS = 6371;

    //임의의 식당의 위도와 경도를 사용자의 위도와 경도로 지정 (restaurantId = 8)
    private final Double lat = 37.2738734428;
    private final Double lon = 126.9412806228;

    @BeforeEach
    void testDataInit() {
        ratingDataInit.createTestRatingData();
    }

    @DisplayName("사용자 근처의 식당조회")
    @Test
    void findNearByRestaurantTest() {

        //given
        Double range = 1.0;

        UserRestaurantRequestDto restaurantRequestDto = UserRestaurantRequestDto.builder()
                .currentLatitude(String.valueOf(lat))
                .currentLongitude(String.valueOf(lon))
                .range(range)
                .build();

        //when
        List<RestaurantResponseDto> responseRestaurants = userService.findNearbyRestaurant(restaurantRequestDto)
                .getResponseRestaurants();

        //then
        for (RestaurantResponseDto responseRestaurant : responseRestaurants) {
            Assertions.assertThat(
                            calculateDistance(lat, lon, responseRestaurant.getLatitude(),
                                    responseRestaurant.getLongitude()))
                    .isLessThanOrEqualTo(range);
        }
    }


    //두 위도와 경도 사이의 거리를 구하는 메소드
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}