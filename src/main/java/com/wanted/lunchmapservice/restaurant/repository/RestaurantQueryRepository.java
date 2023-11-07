package com.wanted.lunchmapservice.restaurant.repository;

import com.wanted.lunchmapservice.restaurant.dto.RequestRestaurantGetFilterDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantQueryRepository {
    Page<Restaurant> findPageByFilter(Pageable pageable, RequestRestaurantGetFilterDto condition);
}
