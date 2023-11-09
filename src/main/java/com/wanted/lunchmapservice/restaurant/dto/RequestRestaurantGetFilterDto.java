package com.wanted.lunchmapservice.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class RequestRestaurantGetFilterDto {
    private String cityName;
    private String countryName;
}

