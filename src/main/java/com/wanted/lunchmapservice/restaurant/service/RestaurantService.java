package com.wanted.lunchmapservice.restaurant.service;

import com.wanted.lunchmapservice.common.PagingUtil;
import com.wanted.lunchmapservice.restaurant.dto.response.RestaurantListResponseDto;
import com.wanted.lunchmapservice.restaurant.dto.response.RestaurantResponseDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.restaurant.mapper.RestaurantMapper;
import com.wanted.lunchmapservice.restaurant.repository.QuerydslRestaurantRepositoryCustom;
import com.wanted.lunchmapservice.user.dto.request.UserRestaurantRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final QuerydslRestaurantRepositoryCustom qRestaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional(readOnly = true)
    public RestaurantListResponseDto findNearbyRestaurant(UserRestaurantRequestDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getPageSize());
        Page<Restaurant> nearbyRestaurant = qRestaurantRepository.findNearByRestaurant(dto, pageRequest);
        List<RestaurantResponseDto> restaurantResponseDtos = nearbyRestaurant.stream().map(
                restaurantMapper::toRestaurantResponseDto).toList();
        return RestaurantListResponseDto.builder()
                .pagingUtil(PagingUtil.getPagingUtil(nearbyRestaurant))
                .responseRestaurants(restaurantResponseDtos)
                .build();
    }
}