package com.wanted.lunchmapservice.restaurant.dto;

import lombok.Builder;

@Builder
public record RatingDto (String content, String username, int score) {

}
