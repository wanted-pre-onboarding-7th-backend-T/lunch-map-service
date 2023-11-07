package com.wanted.lunchmapservice.user.dto.response;

import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoResponseDto {

    private final Long userId;
    private final String userName;
    private final Double latitude;
    private final Double longitude;
    private final ServiceAccess serviceAccess;
}
