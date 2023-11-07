package com.wanted.lunchmapservice.restaurant.repository;

import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    default Map<String, Restaurant> findAllMap() {
        return findAll().stream()
            .collect(Collectors.toMap(Restaurant::getKey, Function.identity()));
    }
}
