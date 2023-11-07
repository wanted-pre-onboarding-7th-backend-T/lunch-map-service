package com.wanted.lunchmapservice.restaurant.utils;

import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.location.utils.LocationCsvService;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import com.wanted.lunchmapservice.restaurant.repository.RawRestaurantRepository;
import com.wanted.lunchmapservice.restaurant.entity.RawRestaurant;
import com.wanted.lunchmapservice.restaurant.repository.RestaurantRepository;
import com.wanted.lunchmapservice.restaurant.utils.openapi.OpenApiCaller;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantSchedulerService {

    private final OpenApiCaller apiCaller;
    private final LocationCsvService locationCsvService;
    private final RawRestaurantRepository rawRestaurantRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @Scheduled(initialDelay = 30000, fixedDelay = 3600000) //서버 시작 30초 후, 이후에는 1시간 마다
//    @Scheduled(cron = "59 59 23 ? * FRI") //매주 금요일 23시 59분 59초 마다
    public void syncRestaurantDataWithOpenApi() {
        rawRestaurantRepository.findAll(); //영속성 컨텍스트 영속화
        List<RawRestaurant> rawRestaurantList = apiCaller.callApiList();
        rawRestaurantRepository.saveAll(rawRestaurantList); //update or insert
        syncRestaurantData(rawRestaurantList);
    }

    private void syncRestaurantData(List<RawRestaurant> rawRestaurantList) {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        for (RawRestaurant rawRestaurant : rawRestaurantList) {
            restaurantList.stream()
                .filter(restaurant -> restaurant.isSame(rawRestaurant))
                .findFirst()
                .ifPresentOrElse(restaurant -> updateData(rawRestaurant, restaurant),
                    () -> insertData(rawRestaurant));
        }
    }

    private void updateData(RawRestaurant rawRestaurant, Restaurant restaurant) {
        Location location = locationCsvService.getLocation(rawRestaurant.getLocationCode());
        restaurant.update(location, rawRestaurant);
    }

    private void insertData(RawRestaurant rawRestaurant) {
        Location location = locationCsvService.getLocation(rawRestaurant.getLocationCode());
        Restaurant restaurant = Restaurant.of(location, rawRestaurant);
        restaurantRepository.save(restaurant);
    }
}
