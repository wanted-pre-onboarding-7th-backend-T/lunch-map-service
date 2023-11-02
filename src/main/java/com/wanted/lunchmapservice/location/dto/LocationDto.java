package com.wanted.lunchmapservice.location.dto;

import lombok.Builder;

@Builder
public record LocationDto(String cityName,
                          String countryName,
                          Double longitude,
                          Double latitude) {

}
