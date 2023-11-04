package com.wanted.lunchmapservice.user.dto.request;

import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

    private Double latitude;
    private Double longitude;
    private ServiceAccess serviceAccess;
}
