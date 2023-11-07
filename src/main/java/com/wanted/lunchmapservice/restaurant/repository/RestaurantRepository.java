package com.wanted.lunchmapservice.restaurant.repository;

import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantQueryRepository {

    @Query("SELECT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.ratingList rr " +
            "LEFT JOIN FETCH rr.user " +
            "WHERE r.id = :id")
    Optional<Restaurant> findByIdFetch(@Param("id") Long id);
  
    default Map<String, Restaurant> findAllMap() {
        return findAll().stream()
            .collect(Collectors.toMap(Restaurant::getKey, Function.identity()));
    }
}
