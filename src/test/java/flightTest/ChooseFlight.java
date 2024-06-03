package flightTest;

import com.javadeveloperzone.service.FlightRepository;
import com.javadeveloperzone.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChooseFlight {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void testChooseFlight() {
        // Arrange
        int userId = 1;
        int flightId = 2;

        // Act
        flightService.chooseFlight(userId, flightId);

        // Assert
        Mockito.verify(flightRepository, Mockito.times(1)).chooseFlight(userId, flightId);
    }
}