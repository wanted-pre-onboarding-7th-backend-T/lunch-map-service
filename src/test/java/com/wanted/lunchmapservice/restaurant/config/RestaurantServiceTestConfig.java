package com.wanted.lunchmapservice.restaurant.config;

import com.wanted.lunchmapservice.restaurant.mapper.RestaurantMapper;
import com.wanted.lunchmapservice.restaurant.mock.RestaurantMock;
import com.wanted.lunchmapservice.restaurant.repository.QuerydslRestaurantRepositoryCustom;
import com.wanted.lunchmapservice.restaurant.repository.QuerydslRestaurantRepositoryImpl;
import com.wanted.lunchmapservice.restaurant.repository.RestaurantRepository;
import com.wanted.lunchmapservice.restaurant.service.RestaurantGetService;
import com.wanted.lunchmapservice.restaurant.service.RestaurantService;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RestaurantServiceTestConfig {

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public RestaurantGetService restaurantGetService() {
        return new RestaurantGetService(restaurantRepository(), restaurantMapper());
    }

    @Bean
    public RestaurantMapper restaurantMapper() {
        return new RestaurantMapper();
    }

    @Bean
    public RestaurantMock restaurantMock() {
        return new RestaurantMock();
    }

    @Bean
    public RestaurantService restaurantService() {
        return new RestaurantService(querydslRestaurantRepositoryCustom(), restaurantMapper());
    }

    @Bean
    public QuerydslRestaurantRepositoryCustom querydslRestaurantRepositoryCustom() {
        return Mockito.mock(QuerydslRestaurantRepositoryImpl.class);
    }
}
