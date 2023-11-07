package com.wanted.lunchmapservice.restaurant.config;

import com.wanted.lunchmapservice.restaurant.mock.RestaurantMock;
import com.wanted.lunchmapservice.restaurant.service.RestaurantGetService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantControllerTestConfig extends AuthTestConfig{

    @Bean
    public RestaurantGetService restaurantGetService() {
        return Mockito.mock(RestaurantGetService.class);
    }

    @Bean
    public RestaurantMock restaurantMock() {
        return new RestaurantMock();
    }
}
