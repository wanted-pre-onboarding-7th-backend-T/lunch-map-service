package com.wanted.lunchmapservice.rating.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingCreateRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer score;

    @NotEmpty
    private String content;
}
