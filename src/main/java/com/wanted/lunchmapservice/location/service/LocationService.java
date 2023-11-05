package com.wanted.lunchmapservice.location.service;

import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.location.repository.LocationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public List<Location> syncLocationData(List<Location> csvLocationList) {
        List<Location> locationList = locationRepository.findAll();
        List<Location> newLocationList = new ArrayList<>();
        for (Location csvLocation : csvLocationList) {
            locationList.stream()
                .filter(location -> location.getCode().equals(csvLocation.getCode()))
                .findFirst()
                .ifPresentOrElse(location -> location.update(csvLocation),
                    () -> newLocationList.add(csvLocation));
        }

        locationList.addAll(newLocationList);
        locationRepository.saveAll(locationList); //update or insert
        return locationList;
    }
}
