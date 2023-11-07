package com.wanted.lunchmapservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.wanted.lunchmapservice.common.exception.ResourceNotFoundException;
import com.wanted.lunchmapservice.user.dto.response.UserInfoResponseDto;
import com.wanted.lunchmapservice.user.entity.User;
import com.wanted.lunchmapservice.user.repository.UserRepository;
import com.wanted.lunchmapservice.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @Test
    public void whenGetUserInfo_thenReturnsUserInfo() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUserName("John Doe");

        given(userRepository.save(any(User.class))).willReturn(user);

        // When
        UserInfoResponseDto response = userService.getUserInfo(userId);

        // Then
        assertNotNull(response);
        assertEquals(userId, response.getId());
        assertEquals("John Doe", response.getUserName());
    }

    @Test
    public void whenGetUserInfoForNonExistentUser_thenThrowsResourceNotFoundException() {
        // Given
        Long userId = 999L; // 존재하지 않는 사용자 ID

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserInfo(userId));
    }

}
