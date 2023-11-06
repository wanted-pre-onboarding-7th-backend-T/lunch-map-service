package com.wanted.lunchmapservice.restaurant;

import com.wanted.lunchmapservice.restaurant.dto.response.RestaurantResponseDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantResponseDto toRestaurantResponseDto(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .locationId(restaurant.getLocation().getId())
                .name(restaurant.getName())
                .lotNumberAddress(restaurant.getLotNumberAddress())
                .roadNameAddress(restaurant.getRoadNameAddress())
                .zipCode(restaurant.getZipCode())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .averageScore(restaurant.getAverageScore())
                .build();
    }

}