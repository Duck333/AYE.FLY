package planeTest;

import com.javadeveloperzone.entity.Plane;
import com.javadeveloperzone.service.PlaneRepository;
import com.javadeveloperzone.service.PlaneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePlaneTest {

    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private PlaneService planeService;

    @Test
    public void testCreatePlane() {
        // Arrange
        Plane plane = new Plane();
        plane.setPlaneName("Boeing 747");

        // Act
        planeService.createPlane(plane);

        // Assert
        Mockito.verify(planeRepository, Mockito.times(1)).save(plane);
        Assertions.assertEquals("Boeing 747", plane.getPlaneName());
    }
}