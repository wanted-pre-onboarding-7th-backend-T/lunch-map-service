package com.wanted.lunchmapservice.user.enums;

import com.wanted.lunchmapservice.common.exception.CommonException;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Sorting {

    ORDER_BY_DISTANCE("distance"),
    ORDER_BY_RATING("rating");

    private final String sortingOption;

    Sorting(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    public static Sorting parse(String sorting) {
        return Arrays.stream(Sorting.values()).
                filter(value -> value.sortingOption.equalsIgnoreCase(sorting))
                .findFirst()
                .orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "유효하지 않은 정렬기준입니다."));
    }

}