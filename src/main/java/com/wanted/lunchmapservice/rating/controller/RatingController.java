package com.wanted.lunchmapservice.rating.controller;

import com.wanted.lunchmapservice.rating.dto.RatingCreateRequestDto;
import com.wanted.lunchmapservice.rating.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Void> createRating(@RequestBody @Valid RatingCreateRequestDto requestDto) {
        ratingService.createRating(requestDto);
        return ResponseEntity.noContent().build();
    }
}