package com.wanted.lunchmapservice.restaurant.dto;


import com.wanted.lunchmapservice.location.dto.LocationDto;
import lombok.Builder;

@Builder
public record GetRestaurantSimpleResponseDto(Long restaurantId, String restaurantName,
                                             String lotNumberAddress, String roadNameAddress,
                                             Double longitude, Double latitude, LocationDto location,
                                             Double averageScore) {

}