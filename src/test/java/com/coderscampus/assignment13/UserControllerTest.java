package com.coderscampus.assignment13;

import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AddressService;
import com.coderscampus.assignment13.service.UserService;
import com.coderscampus.assignment13.web.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

        @InjectMocks
        UserController userController;

        @Mock
        UserService userService;

        @Mock
        AddressService addressService;
        @BeforeEach
        public void init() {
            MockitoAnnotations.initMocks(this);
        }

    @Test
    public void testPostOneUser() {
        User user = new User();
        user.setUserId(1L);
        User currentUser = new User();
        currentUser.setUserId(1L);
        when(userService.saveUser(any(User.class))).thenReturn(user);
        when(userService.findByIdAccounts(any(Long.class))).thenReturn(currentUser);

        String result = userController.postOneUser(user, 1L);
        verify(userService, times(1)).saveUser(user);
        assertEquals("redirect:/users" + user.getUserId(), result);
    }

    @Test
    public void testUpdateSingleUser() {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        User updatedUser = new User();
        updatedUser.setUserId(1L);
        updatedUser.setUsername("updatedUsername");

        when(userService.findByIdAccounts(any(Long.class))).thenReturn(user);
        when(userService.saveUser(any(User.class))).thenReturn(updatedUser);

        String result = userController.postOneUser(updatedUser, 1L);

        verify(userService, times(1)).saveUser(updatedUser);
        assertEquals("redirect:/users" + updatedUser.getUserId(), result);
    }
}
