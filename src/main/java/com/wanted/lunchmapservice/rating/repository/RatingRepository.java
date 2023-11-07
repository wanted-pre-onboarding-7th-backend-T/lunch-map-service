package com.wanted.lunchmapservice.rating.repository;

import com.wanted.lunchmapservice.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
