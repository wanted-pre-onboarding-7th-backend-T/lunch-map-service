package com.wanted.lunchmapservice.restaurant.dto;


import com.wanted.lunchmapservice.location.dto.ResponseLocationDto;
import lombok.Builder;

@Builder
public record ResponseGetRestaurantSimpleDto(Long restaurantId, String restaurantName,
                                             String lotNumberAddress, String roadNameAddress,
                                             Double longitude, Double latitude, ResponseLocationDto location,
                                             Double averageScore) {

}