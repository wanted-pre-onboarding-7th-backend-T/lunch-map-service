package com.wanted.lunchmapservice.user.dto.request;

import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserUpdateRequestDto {
    @Setter
    private Double latitude;
    @Setter
    private Double longitude;
    @Setter
    private ServiceAccess serviceAccess;
    @Setter
    private Long userId;

}
