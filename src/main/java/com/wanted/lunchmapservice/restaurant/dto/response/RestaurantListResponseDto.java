package com.wanted.lunchmapservice.restaurant.dto.response;

import com.wanted.lunchmapservice.common.dto.ResponseListDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class RestaurantListResponseDto extends ResponseListDto {

    List<RestaurantResponseDto> responseRestaurants;
}