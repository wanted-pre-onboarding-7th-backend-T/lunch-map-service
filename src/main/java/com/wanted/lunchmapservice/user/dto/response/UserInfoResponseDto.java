package com.wanted.lunchmapservice.user.dto.response;

import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoResponseDto {

    private final Long id;
    private final String userName;
    private final Double latitude;
    private final Double longitude;
    private final ServiceAccess serviceAccess;

    public UserInfoResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
        this.serviceAccess = user.getServiceAccess();
    }
}
