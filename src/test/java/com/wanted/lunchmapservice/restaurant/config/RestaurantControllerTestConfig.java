package com.wanted.lunchmapservice.restaurant.config;

import com.wanted.lunchmapservice.restaurant.mock.RestaurantMock;
import com.wanted.lunchmapservice.restaurant.service.RestaurantGetService;
import com.wanted.lunchmapservice.restaurant.service.RestaurantService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RestaurantControllerTestConfig extends AuthTestConfig{

    @Bean
    public RestaurantGetService restaurantGetService() {
        return Mockito.mock(RestaurantGetService.class);
    }

    @Bean
    public RestaurantMock restaurantMock() {
        return new RestaurantMock();
    }

    @Bean
    public RestaurantService restaurantService() {
        return Mockito.mock(RestaurantService.class);
    }
}
