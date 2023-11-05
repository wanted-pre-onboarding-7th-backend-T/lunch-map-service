package com.wanted.lunchmapservice.user.controller;

import com.wanted.lunchmapservice.common.dto.ResponseDto;
import com.wanted.lunchmapservice.user.dto.request.UserPostRequestDto;
import com.wanted.lunchmapservice.user.dto.request.UserUpdateRequestDto;
import com.wanted.lunchmapservice.user.dto.response.UserIdResponseDto;
import com.wanted.lunchmapservice.user.dto.response.UserInfoResponseDto;
import com.wanted.lunchmapservice.user.service.UserService;
import com.wanted.lunchmapservice.user.utils.UriCreator;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserInfoResponseDto>> updateUser(
        @PathVariable Long userId,
        @RequestBody @Valid UserUpdateRequestDto updateDto) {
        UserInfoResponseDto result = service.updateUser(userId, updateDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value(), result, HttpStatus.OK.getReasonPhrase()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserInfoResponseDto>> getUser(
        @PathVariable Long userId) {
        UserInfoResponseDto result = service.getUser(userId);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value(), result, HttpStatus.OK.getReasonPhrase()));
    }
}
