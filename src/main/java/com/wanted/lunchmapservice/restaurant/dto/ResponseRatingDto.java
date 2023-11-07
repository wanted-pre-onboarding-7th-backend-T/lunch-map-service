package com.wanted.lunchmapservice.restaurant.dto;

import lombok.Builder;

@Builder
public record ResponseRatingDto(String content, String username, int score) {

}
