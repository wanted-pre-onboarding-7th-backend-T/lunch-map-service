package com.wanted.lunchmapservice.restaurant.dto;

import com.wanted.lunchmapservice.location.dto.LocationDto;
import lombok.Builder;

@Builder
public record GetRestaurantDetailResponseDto(Long restaurantId, String restaurantName,
                                             String lotNumberAddress, String roadNameAddress,
                                             String zipCode, LocationDto location,
                                             Double averageScore) {

}
