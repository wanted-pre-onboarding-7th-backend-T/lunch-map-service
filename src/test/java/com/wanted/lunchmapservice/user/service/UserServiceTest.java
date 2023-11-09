package com.wanted.lunchmapservice.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import com.wanted.lunchmapservice.common.exception.ResourceNotFoundException;
import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.wanted.lunchmapservice.user.dto.request.UserUpdateRequestDto;
import com.wanted.lunchmapservice.user.enums.ServiceAccess;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User user;

    @Test
    public void whenUpdateUserSettings_thenUpdateFields() {
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
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);
            assertEquals(newServiceAccess, user.getServiceAccess());
            assertEquals(newLatitude, user.getLatitude());
            assertEquals(newLongitude, user.getLongitude());
            return null;
        }).when(userRepository).save(any(User.class));

        // when
        userService.updateUserSettings(updateDto);

        // then
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void whenUpdateUserSettings_withNonExistentUser_thenThrowException() {
        // given
        Long userId = 1L;
        UserUpdateRequestDto updateDto = new UserUpdateRequestDto();
        updateDto.setUserId(userId);

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUserSettings(updateDto);
        });

        // then
        assertEquals("User not found", exception.getMessage());
    }
}

