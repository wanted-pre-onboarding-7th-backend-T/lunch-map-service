package com.wanted.lunchmapservice.restaurant.controller;

import com.wanted.lunchmapservice.common.dto.ResponseDto;
import com.wanted.lunchmapservice.restaurant.dto.GetRestaurantDetailResponseDto;
import com.wanted.lunchmapservice.restaurant.service.RestaurantGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantGetService getService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<ResponseDto<GetRestaurantDetailResponseDto>> getRestaurantDetail(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(getService.getDetails(restaurantId));
    }
}
