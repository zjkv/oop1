package company.rental;

import company.BaseTest;
import company.ClientId;
import company.rental.session.SessionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionServiceTest extends BaseTest {//Napisz zamykanie sessji i sprawdz co jak ma 5 sesji zamknie 2 czy bedzie ok i prace sie aktywuje ;;

    ClientId clientId = new ClientId(1L);
    ScooterId scooterId1 = new ScooterId(101L);
    ScooterId scooterId2 = new ScooterId(102L);
    ScooterId scooterId3 = new ScooterId(103L);
    ScooterId scooterId4 = new ScooterId(104L);

    ScooterId scooterId5 = new ScooterId(105L);

    SessionService sessionService = new SessionService(testDB);

    @Test
    void shouldCreateNewSession() {
        var priceFactor = new PriceFactor(1.0);

        sessionService.createSession(clientId, scooterId1, priceFactor);

        assertEquals(1, testDB.getClientActiveSession(clientId.id()).size());
    }

    @Test
    void shouldCreate4ActiveSessions() {
        var priceFactor = new PriceFactor(1.0);

        sessionService.createSession(clientId, scooterId1, priceFactor);
        sessionService.createSession(clientId, scooterId2, priceFactor);
        sessionService.createSession(clientId, scooterId3, priceFactor);
        sessionService.createSession(clientId, scooterId4, priceFactor);

        assertEquals(4, testDB.getClientActiveSession(clientId.id()).size());
    }

    @Test
    void shouldCloseActiveSession() {
        var priceFactor = new PriceFactor(1.0);

        sessionService.createSession(clientId, scooterId1, priceFactor);

        assertEquals(1, testDB.getClientActiveSession(clientId.id()).size());

        sessionService.closeSession(clientId, scooterId1);

        assertEquals(0, testDB.getClientActiveSession(clientId.id()).size());

    }

}