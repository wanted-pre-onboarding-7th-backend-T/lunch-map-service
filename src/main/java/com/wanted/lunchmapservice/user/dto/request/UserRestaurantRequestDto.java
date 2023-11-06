package com.wanted.lunchmapservice.user.dto.request;

import com.wanted.lunchmapservice.common.dto.RequestPageDto;
import com.wanted.lunchmapservice.user.enums.Sorting;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRestaurantRequestDto extends RequestPageDto {

    @NotNull(message = "추천받을 위치의 경도를 입력해 주세요.")
    private String currentLongitude;
    @NotNull(message = "추천받을 위치의 위도를 입력해 주세요.")
    private String currentLatitude;
    @NotNull(message = "현재 위치에서 조화하고 싶은 맛집의 반경을 입력해 주세요.")
    private Double range;
    @Builder.Default
    private String sorting = Sorting.ORDER_BY_DISTANCE.getSortingOption();

}