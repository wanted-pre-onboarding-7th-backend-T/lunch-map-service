package com.wanted.lunchmapservice.location.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.location.repository.LocationRepository;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationCsvService {

    private final LocationRepository locationRepository;
    private final static String FILE_NAME = "static/sgg_lat_lon.csv";

    public LocationCsvService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    @Transactional
    public void storeLocationCsvData() {
        List<Location> locationDataList = readLocationCsvData().stream()
            .map(LocationCsvDto::toEntity).toList();
        locationRepository.saveAll(locationDataList); //TODO: 데이터 무결성 고려
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
}
