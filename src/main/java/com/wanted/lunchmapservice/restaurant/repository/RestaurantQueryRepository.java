package com.wanted.lunchmapservice.restaurant.repository;

import com.wanted.lunchmapservice.restaurant.dto.RestaurantGetFilterDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantQueryRepository {
    Page<Restaurant> findPageByFilter(Pageable pageable, RestaurantGetFilterDto condition);
}
