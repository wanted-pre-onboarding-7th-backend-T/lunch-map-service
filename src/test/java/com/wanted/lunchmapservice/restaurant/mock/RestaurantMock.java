package com.wanted.lunchmapservice.restaurant.mock;

import com.wanted.lunchmapservice.common.dto.ResponseDto;
import com.wanted.lunchmapservice.location.dto.LocationDto;
import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.restaurant.dto.GetRestaurantDetailResponseDto;
import com.wanted.lunchmapservice.restaurant.entity.Restaurant;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RestaurantMock {

    private final String name = "아도니스";
    private final String lotNumberAddress = "경기도 가평군 상면 행현리 602-3번지";
    private final String roadNameAddress = "경기도 가평군 상면 수목원로 314-2";
    private final String zipCode = "12448";
    private final Double longitude = 37.7516678333;
    private final Double latitude = 127.3588076752;
    private final Double averageScore = 0.0;
    private final String cityName = "경기도";
    private final String countryName = "가평군";

    public Restaurant getEntity(Long id) {
        return Restaurant.builder()
                .id(id)
                .location(createLocation())
                .name(name)
                .lotNumberAddress(lotNumberAddress)
                .roadNameAddress(roadNameAddress)
                .zipCode(zipCode)
                .longitude(longitude)
                .latitude(latitude)
                .averageScore(averageScore)
                .build();
    }

    public ResponseDto<GetRestaurantDetailResponseDto> getDetailResponse(Long id) {
        return ResponseDto.<GetRestaurantDetailResponseDto>builder()
                .data(createGetDetailDto(id))
                .message(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .build();
    }

    private GetRestaurantDetailResponseDto createGetDetailDto(Long id) {
        return GetRestaurantDetailResponseDto.builder()
                .restaurantId(getEntity(id).getId())
                .averageScore(getEntity(id).getAverageScore())
                .restaurantName(getEntity(id).getName())
                .longitude(getEntity(id).getLongitude())
                .latitude(getEntity(id).getLatitude())
                .lotNumberAddress(getEntity(id).getLotNumberAddress())
                .roadNameAddress(getEntity(id).getRoadNameAddress())
                .location(createLocationDto(getEntity(id).getLocation()))
                .zipCode(getEntity(id).getZipCode())
                .build();
    }

    private LocationDto createLocationDto(Location entity) {
        return LocationDto.builder()
                .cityName(entity.getCityName())
                .countryName(entity.getCountryName())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .id(1L)
                .cityName(cityName)
                .countryName(countryName)
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }

}







