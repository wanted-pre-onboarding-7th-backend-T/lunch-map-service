package com.wanted.lunchmapservice.location.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.location.service.LocationService;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class LocationCsvService {

    private final LocationService locationService;
    private final static Map<String, Location> locationCacheMap = new HashMap<>();
    private final static String FILE_NAME = "static/sgg_lat_lon.csv";

    public LocationCsvService(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    public void storeLocationCsvData() {
        List<Location> csvLocationList = readLocationCsvData().stream()
            .map(LocationCsvDto::toEntity).toList();
        List<Location> locationList = locationService.syncLocationData(csvLocationList);
        for (Location location : locationList) {
            locationCacheMap.put(location.getCode(), location);
        }
    }

    private List<LocationCsvDto> readLocationCsvData() {
        ClassPathResource resource = new ClassPathResource(FILE_NAME);
        try (Reader reader = new FileReader(resource.getFile())) {
            return new CsvToBeanBuilder<LocationCsvDto>(reader)
                .withType(LocationCsvDto.class)
                .withSkipLines(1) //헤더 스킵
                .build().parse();
        } catch (Exception e) {
            return List.of();
        }
    }

    public Location getLocation(String locationCode) {
        return locationCacheMap.get(locationCode);
    }
}
