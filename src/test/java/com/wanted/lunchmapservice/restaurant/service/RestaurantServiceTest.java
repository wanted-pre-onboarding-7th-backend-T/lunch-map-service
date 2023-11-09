package com.wanted.lunchmapservice.restaurant.service;

import static org.mockito.ArgumentMatchers.any;

import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.restaurant.config.RestaurantServiceTestConfig;
import com.wanted.lunchmapservice.restaurant.dto.response.RestaurantResponseDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.restaurant.repository.QuerydslRestaurantRepositoryCustom;
import com.wanted.lunchmapservice.user.dto.request.UserRestaurantRequestDto;
import com.wanted.lunchmapservice.user.enums.Sorting;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(RestaurantServiceTestConfig.class)
class RestaurantServiceTest {

    private final int EARTH_RADIUS = 6371;
    //임의의 식당의 위도와 경도를 사용자의 위도와 경도로 지정 (restaurantId = 8)
    private final Double lat = 37.2738734428;
    private final Double lon = 126.9412806228;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private RestaurantService restaurantService;
    @MockBean
    private QuerydslRestaurantRepositoryCustom restaurantRepository;

    @BeforeEach
    void testDataInit() {
//        ratingDataInit.createTestRatingData();
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

        PageRequest pageRequest = PageRequest.of(0, 30);
        PageImpl<Restaurant> pageEntity = new PageImpl<>(createList(), pageRequest, 30);

        BDDMockito.given(
                restaurantRepository.findNearByRestaurant(any(UserRestaurantRequestDto.class), any(
                        Pageable.class))).willReturn(pageEntity);
        //when
        List<RestaurantResponseDto> responseRestaurants = restaurantService.findNearbyRestaurant(
                        restaurantRequestDto)
                .getResponseRestaurants();

        //then
        for (RestaurantResponseDto responseRestaurant : responseRestaurants) {
            Assertions.assertThat(
                            calculateDistance(lat, lon, responseRestaurant.getLatitude(),
                                    responseRestaurant.getLongitude()))
                    .isLessThanOrEqualTo(range);
        }
    }


    @DisplayName("사용자 근처의 식당은 평점순(높은순)으로 조회")
    @Test
    void orderByRatingTest() {
        //given
        Double range = 3.0;
        UserRestaurantRequestDto requestDto = UserRestaurantRequestDto.builder()
                .currentLatitude(String.valueOf(lat))
                .currentLongitude(String.valueOf(lon))
                .sorting(Sorting.ORDER_BY_RATING)
                .range(range)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 30);
        PageImpl<Restaurant> pageEntity = new PageImpl<>(createList(), pageRequest, 30);

        BDDMockito.given(
                restaurantRepository.findNearByRestaurant(any(UserRestaurantRequestDto.class), any(
                        Pageable.class))).willReturn(pageEntity);

        //when
        List<RestaurantResponseDto> responseRestaurants = restaurantService.findNearbyRestaurant(
                        requestDto)
                .getResponseRestaurants();

        //then
        Assertions.assertThat(responseRestaurants.get(0).getAverageScore())
                .isGreaterThanOrEqualTo(responseRestaurants.get(1).getAverageScore());
    }

    @DisplayName("사용자 근처의 식당은 거리순(가까운순)으로 조회")
    @Test
    void orderByDistanceTest() {
        //given
        Double range = 3.0;
        UserRestaurantRequestDto requestDto = UserRestaurantRequestDto.builder()
                .currentLatitude(String.valueOf(lat))
                .currentLongitude(String.valueOf(lon))
                .range(range)
                .sorting(Sorting.ORDER_BY_DISTANCE)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 30);
        PageImpl<Restaurant> pageEntity = new PageImpl<>(createList(), pageRequest, 30);

        BDDMockito.given(
                restaurantRepository.findNearByRestaurant(any(UserRestaurantRequestDto.class), any(
                        Pageable.class))).willReturn(pageEntity);
        //when
        List<RestaurantResponseDto> responseRestaurants = restaurantService.findNearbyRestaurant(
                        requestDto)
                .getResponseRestaurants();

        //then
        double nearestDistance = calculateDistance(lat, lon,
                responseRestaurants.get(1).getLatitude(),
                responseRestaurants.get(0).getLongitude());
        double secondNearestDistance = calculateDistance(lat, lon,
                responseRestaurants.get(2).getLatitude(),
                responseRestaurants.get(0).getLongitude());
        Assertions.assertThat(nearestDistance).isLessThanOrEqualTo(secondNearestDistance);
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

    private List<Restaurant> createList() {
        List<Restaurant> list = new ArrayList<>();
        for (Long i = 1L; i < 30; i++) {
            list.add(createEntity(i));
        }
        return list;
    }

    private Restaurant createEntity(Long id) {
        return Restaurant.builder()
                .id(id)
                .longitude(lon)
                .latitude(lat)
                .location(new Location(id, "경남" + id, "진주시" + id, lon, lat))
                .averageScore((double) (id / 10))
                .ratingList(new ArrayList<>())
                .build();
    }
}