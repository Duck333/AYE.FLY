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
class UpdatePlaneNameByIdTest {

    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private PlaneService planeService;

    @Test
    void shouldUpdatePlaneNameSuccessfully() {
        // Arrange
        int planeId = 1;
        String newPlaneName = "New Plane Name";

        // Act
        planeService.updatePlaneNameById(planeId, newPlaneName);

        // Assert
        Mockito.verify(planeRepository, Mockito.times(1)).updatePlaneNameById(planeId, newPlaneName);
    }
}