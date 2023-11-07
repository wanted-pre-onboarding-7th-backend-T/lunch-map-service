package com.wanted.lunchmapservice.location.service;

import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.location.repository.LocationRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public List<Location> syncLocationData(List<Location> csvLocationList) {
        Map<String, Location> locationMap = locationRepository.findAllMap();
        for (Location csvLocation : csvLocationList) {
            if (locationMap.containsKey(csvLocation.getCode())) {
                locationMap.get(csvLocation.getCode()).update(csvLocation);
            } else {
                locationMap.put(csvLocation.getCode(), csvLocation);
            }
        }
        List<Location> updatedLocaionList = locationMap.values().stream().toList();
        locationRepository.saveAll(updatedLocaionList); //update or insert
        return updatedLocaionList;
    }
}
