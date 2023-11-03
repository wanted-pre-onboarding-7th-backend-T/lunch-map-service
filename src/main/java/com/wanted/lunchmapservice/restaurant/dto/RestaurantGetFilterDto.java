package com.wanted.lunchmapservice.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantGetFilterDto {
    private String cityName;
    private String countryName;
}

