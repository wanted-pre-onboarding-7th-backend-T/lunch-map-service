package com.wanted.lunchmapservice.restaurant.repository;

import static com.wanted.lunchmapservice.restaurant.entity.QRestaurant.restaurant;
import static com.wanted.lunchmapservice.user.enums.Sorting.ORDER_BY_RATING;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.user.dto.request.UserRestaurantRequestDto;
import com.wanted.lunchmapservice.user.enums.Sorting;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuerydslRestaurantRepositoryImpl implements QuerydslRestaurantRepositoryCustom {

    private final JPAQueryFactory query;
    private static final Integer EARTH_RADIUS = 6371;

    @Override
    public Page<Restaurant> findNearByRestaurant(UserRestaurantRequestDto dto, Pageable pageable) {
        Double userLat = Double.parseDouble(dto.getCurrentLatitude());
        Double userLng = Double.parseDouble(dto.getCurrentLongitude());
        List<Restaurant> nearByRestaurants = query
                .select(restaurant)
                .from(restaurant)
                .where(acosExpression(userLat, userLng).loe(dto.getRange()))
                .orderBy(getOrderByExpression(userLat, userLng, dto.getSorting()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(nearByRestaurants, pageable, countRestaurantNearBy(dto));
    }

    private Long countRestaurantNearBy(UserRestaurantRequestDto dto) {
        Double userLat = Double.parseDouble(dto.getCurrentLatitude());
        Double userLng = Double.parseDouble(dto.getCurrentLongitude());
        return query.select(restaurant.count())
                .from(restaurant)
                .where(acosExpression(userLat, userLng).loe(dto.getRange()))
                .fetchOne();
    }

    private OrderSpecifier<?> getOrderByExpression(Double userLat, Double userLng, Sorting sorting) {
        if (sorting.equals(ORDER_BY_RATING)) {
            return restaurant.averageScore.desc();
        }
        return acosExpression(userLat, userLng).asc();
    }

    private NumberTemplate<Double> acosExpression(Double userLat, Double userLng) {
        return Expressions.numberTemplate(Double.class,
                "{0} * acos(cos(radians({1})) * cos(radians({2})) * cos(radians({3}) - radians({4})) + "
                        + "sin(radians({1})) * sin(radians({2})))",
                EARTH_RADIUS, userLat, restaurant.latitude, userLng, restaurant.longitude);
    }
}