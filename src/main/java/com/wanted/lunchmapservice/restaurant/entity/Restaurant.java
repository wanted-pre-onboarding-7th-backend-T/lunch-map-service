package com.wanted.lunchmapservice.restaurant.entity;

import static jakarta.persistence.FetchType.LAZY;

import com.wanted.lunchmapservice.common.BaseTime;
import com.wanted.lunchmapservice.location.entity.Location;
import com.wanted.lunchmapservice.rating.Rating;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Entity
public class Restaurant extends BaseTime {
    @Id
    @Column(name = "restaurant_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ColumnDefault("'EMPTY'")
    @Column(name = "name", nullable = false)
    private String name;

    @ColumnDefault("'EMPTY'")
    @Column(name = "lot_number_address", nullable = false)
    private String lotNumberAddress;

    @ColumnDefault("'EMPTY'")
    @Column(name = "road_name_address", nullable = false)
    private String roadNameAddress;

    @ColumnDefault("'EMPTY'")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @ColumnDefault("-1")
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ColumnDefault("-1")
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @ColumnDefault("-1")
    @Column(name = "average_score", nullable = false)
    private Double averageScore;

    @Default
    @Setter
    @OneToMany(fetch = LAZY,cascade = CascadeType.PERSIST,mappedBy = "restaurant")
    private List<Rating> ratingList = new ArrayList<>();

    public void sortRatingList() {
        ratingList.sort((d1, d2) -> d2.getId().compareTo(d1.getId()));
    }
}
