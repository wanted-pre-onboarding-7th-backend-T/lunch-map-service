package com.wanted.lunchmapservice.user.controller;

import com.wanted.lunchmapservice.common.dto.ResponseDto;
import com.wanted.lunchmapservice.user.dto.request.UserPostRequestDto;
import com.wanted.lunchmapservice.user.dto.request.UserUpdateRequestDto;
import com.wanted.lunchmapservice.user.dto.response.UserIdResponseDto;
import com.wanted.lunchmapservice.user.service.UserService;
import com.wanted.lunchmapservice.user.utils.UriCreator;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private static final String URL = "/api/users";
    private final UserService service;

    @PostMapping
    public ResponseEntity<ResponseDto<UserIdResponseDto>> postUser(
            @RequestBody @Valid UserPostRequestDto post) {
        ResponseDto<UserIdResponseDto> result = service.saveUser(post);
        URI location = UriCreator.createUri(URL, result.getData().getUserId());
        return ResponseEntity.created(location).body(result);
    }
    @PatchMapping("/info")
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UserUpdateRequestDto settingsDto) {
        // You may want to include authentication and authorization checks
        // to ensure that the user is permitted to update the information.

        // The service layer would handle the update logic
        userService.updateUserSettings(settingsDto);

        // Return an appropriate response, such as:
        return ResponseEntity.ok().build();
    }
}
