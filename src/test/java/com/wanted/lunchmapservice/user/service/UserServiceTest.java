package com.wanted.lunchmapservice.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wanted.lunchmapservice.common.exception.ResourceNotFoundException;
import com.wanted.lunchmapservice.user.dto.request.UserUpdateRequestDto;
import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 업데이트 : 성공")
    void whenUpdateUserSettings_thenUpdateFields() {
        // given
        Long userId = 1L;
        ServiceAccess newServiceAccess = ServiceAccess.LUNCH;
        Double newLatitude = 40.7128;
        Double newLongitude = -74.0060;
        User existingUser = new User(); // Mock existing user details
        existingUser.setId(userId);
        UserUpdateRequestDto updateDto = new UserUpdateRequestDto();
        updateDto.setUserId(userId);
        updateDto.setServiceAccess(newServiceAccess);
        updateDto.setLatitude(newLatitude);
        updateDto.setLongitude(newLongitude);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));


        // when
        userService.updateUserSettings(updateDto);
        // then
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("사용자 업데이트 : 실패[사용자 조회 실패]")
    void whenUpdateUserSettings_withNonExistentUser_thenThrowException() {
        // given
        Long userId = 1L;
        UserUpdateRequestDto updateDto = new UserUpdateRequestDto();
        updateDto.setUserId(userId);

        // when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUserSettings(updateDto);
        });

        // then
        assertEquals("User not found", exception.getMessage());
    }
}

