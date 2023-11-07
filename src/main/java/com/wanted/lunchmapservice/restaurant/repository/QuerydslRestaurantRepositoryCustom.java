package com.wanted.lunchmapservice.restaurant.repository;

import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.user.dto.request.UserRestaurantRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuerydslRestaurantRepositoryCustom {

    Page<Restaurant> findNearByRestaurant(UserRestaurantRequestDto dto, Pageable pageable);

}