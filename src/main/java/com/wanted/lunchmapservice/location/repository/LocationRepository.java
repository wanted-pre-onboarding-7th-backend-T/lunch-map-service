package com.wanted.lunchmapservice.location.repository;

import com.wanted.lunchmapservice.location.entity.Location;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    default Map<String, Location> findAllMap() {
        return findAll().stream()
            .collect(Collectors.toMap(Location::getCode, Function.identity()));
    }
}
