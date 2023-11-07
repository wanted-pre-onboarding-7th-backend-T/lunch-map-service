package com.wanted.lunchmapservice.restaurant.service;

import com.wanted.lunchmapservice.common.dto.CustomPage;
import com.wanted.lunchmapservice.common.dto.ResponseDto;
import com.wanted.lunchmapservice.common.exception.CommonException;
import com.wanted.lunchmapservice.restaurant.dto.GetRestaurantDetailResponseDto;
import com.wanted.lunchmapservice.restaurant.dto.GetRestaurantSimpleResponseDto;
import com.wanted.lunchmapservice.restaurant.dto.RestaurantGetFilterDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.restaurant.mapper.RestaurantMapper;
import com.wanted.lunchmapservice.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantGetService {
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    public ResponseDto<GetRestaurantDetailResponseDto> getDetails(Long restaurantId) {
        Restaurant result = validRestaurant(restaurantId);
        result.sortRatingList();
        return mapper.toResponseDto(result);
    }

    public ResponseDto<CustomPage<GetRestaurantSimpleResponseDto>> getRestaurant(RestaurantGetFilterDto request, Pageable pageable) {
        return mapper.toResponseDto(repository.findPageByFilter(pageable,request));
    }

    private Restaurant validRestaurant(Long restaurantId) {
        return repository.findByIdFetch(restaurantId)
                .orElseThrow(() -> new CommonException(HttpStatus.CONFLICT, "맛집 정보가 존재하지 않습니다."));
    }

}
