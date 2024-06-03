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
class DeleteAllPlanesTest {

    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private PlaneService planeService;

    @Test
    void shouldDeleteAllPlanes() {
        // Act
        planeService.deleteAllPlanes();

        // Assert
        Mockito.verify(planeRepository, Mockito.times(1)).deleteAll();
    }
}