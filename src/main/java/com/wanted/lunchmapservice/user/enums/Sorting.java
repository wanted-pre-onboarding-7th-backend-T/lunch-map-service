package com.wanted.lunchmapservice.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sorting {

    ORDER_BY_DISTANCE("distance"),
    ORDER_BY_RATING("rating");

    private final String sortingOption;

    Sorting(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    @JsonCreator
    public static Sorting from(String value) {
        for (Sorting sorting : Sorting.values()) {
            if (sorting.getValue().equals(value)) {
                return sorting;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return sortingOption;
    }

}