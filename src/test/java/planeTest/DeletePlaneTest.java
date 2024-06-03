package planeTest;

import com.javadeveloperzone.service.PlaneRepository;
import com.javadeveloperzone.service.PlaneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePlaneTest {

    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private PlaneService planeService;

    @Test
    void shouldDeletePlaneSuccessfully() {
        // Arrange
        int planeId = 1;

        // Act
        planeService.deletePlaneById(planeId);

        // Assert
        Mockito.verify(planeRepository, Mockito.times(1)).deleteById(planeId);
    }
}