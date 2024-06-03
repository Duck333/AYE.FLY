package flightTest;

import com.javadeveloperzone.entity.Flight;
import com.javadeveloperzone.service.FlightRepository;
import com.javadeveloperzone.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateFlightTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void shouldCreateFlight() {
        // Arrange
        Flight flight = new Flight();
        flight.setFlightName("Test Flight");
        flight.setPlaneId(1);

        // Act
        flightService.createFlight(flight);

        // Assert
        Mockito.verify(flightRepository, Mockito.times(1)).save(flight);
    }
}