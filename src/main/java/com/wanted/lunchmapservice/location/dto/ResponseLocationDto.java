package com.wanted.lunchmapservice.location.dto;

import lombok.Builder;

@Builder
public record ResponseLocationDto(String cityName,
                                  String countryName,
                                  Double longitude,
                                  Double latitude) {

}
