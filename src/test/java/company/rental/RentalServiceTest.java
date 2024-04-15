package company.rental;

import company.ClientId;
import company.rental.session.SessionService;
import company.repository.TestDB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    SessionService sessionServiceMock;

    @Mock
    FactorService factorServiceMock;

    @Mock
    TestDB testDB;

    @Test
    public void shouldCallNeededServices() {
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        int numberOfActiveSessions = 1;
        RentalService rentalService = new RentalService(sessionServiceMock, testDB, factorServiceMock);

        when(sessionServiceMock.getActiveClientSessionsNumber(clientId)).thenReturn(numberOfActiveSessions);

        rentalService.rentScooter(clientId, scooterId);
        verify(sessionServiceMock).getActiveClientSessionsNumber(clientId);
        verify(factorServiceMock).calculateFactor(numberOfActiveSessions);
    }
}