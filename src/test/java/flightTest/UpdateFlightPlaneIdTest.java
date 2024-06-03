package flightTest;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.service.FlightRepository;
import com.javadeveloperzone.service.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateFlightPlaneIdTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void shouldUpdateFlightPlaneIdByFlightId() {
        // Arrange
        int flightId = 1;
        int newFlightPlaneId = 2;

        // Act
        flightService.updateFlightPlaneIdByFlightId(flightId, newFlightPlaneId);

        // Assert
        Mockito.verify(flightRepository, Mockito.times(1)).updateFlightPlaneIdByFlightId(flightId, newFlightPlaneId);
    }
}