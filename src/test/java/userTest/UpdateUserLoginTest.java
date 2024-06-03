package userTest;

import com.javadeveloperzone.entity.User;
import com.javadeveloperzone.service.UserRepository;
import com.javadeveloperzone.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserLoginTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldUpdateUserLoginById() {
        // Arrange
        int userId = 1;
        String newUserLogin = "mike";

        // Act
        userService.updateUserLoginById(userId, newUserLogin);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).updateUserLoginById(userId, newUserLogin);
    }
}