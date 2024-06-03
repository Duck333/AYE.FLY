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
class CreateUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        // Arrange
        User user = new User();
        user.setUserName("John Doe");
        user.setUserLogin("johndoe");
        user.setUserPassword("password123");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.createUser(user);

        // Assert
        Assertions.assertEquals(user, createdUser);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}