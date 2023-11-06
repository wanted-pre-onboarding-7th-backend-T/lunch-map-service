package com.wanted.lunchmapservice.user.dto.request;

import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private Double latitude;
    private Double longitude;
    private ServiceAccess serviceAccess;

    @Getter
    private Long userId;

    public void setServiceAccess(ServiceAccess serviceAccess) {
        this.serviceAccess = serviceAccess;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
