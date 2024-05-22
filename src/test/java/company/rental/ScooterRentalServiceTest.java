package company.rental;

import company.BaseTest;
import company.ClientId;
import company.maintanace.Latitude;
import company.maintanace.Longitude;
import company.maintanace.Position;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static company.repository.TestDB.*;
import static org.junit.jupiter.api.Assertions.*;

class ScooterRentalServiceTest extends BaseTest {

    @Test
    void shouldDo() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        Longitude longitude = new Longitude(15F);
        Latitude latitude = new Latitude(50F);

        Position position = new Position(latitude, longitude); //Position Value object

        int expectedLoyaltyPoints = 0;
        float expectedChargeAmount = 1f;
        int expectedTransactionCounter = 33;

        //when
        ScooterRentalService.rentScooter(clientId, scooterId, testDB);
        ScooterRentalService.returnScooter(clientId, scooterId, position, testDB); //I put there ref to test Db just to use only one. xD It is lets say quick fix


        //then
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        assertEquals(expectedLoyaltyPoints, clientData.get(LOYALTY_POINTS));
        assertEquals(expectedChargeAmount, clientData.get(CHARGE_AMOUNT));
        assertEquals(expectedTransactionCounter, clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));

    }

    @Disabled
    @Test
    void shouldThrowExceptionWhenCordsNotFromPoland() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        Longitude longitude = new Longitude(2F); //Cords not from poland
        Latitude latitude = new Latitude(3F);

        //line bellow thorw exception
        Position position = new Position(latitude, longitude); //Position Value object

        //when
        ScooterRentalService.rentScooter(clientId, scooterId, testDB);
        ScooterRentalService.returnScooter(clientId, scooterId, position, testDB); //I put there ref to test Db just to use only one. xD It is lets say quick fix


        //expected
        assertThrows(RuntimeException.class,
                () -> ScooterRentalService.returnScooter(clientId, scooterId, position, testDB));
        // assertEquals(expectedNeedsToChargeBattery, clientData.get(NEEDS_TO_CHARGE_BATTERY)); //not set up

    }

    @Test
    void shouldRentScooter() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);

        //when
        var clientData = testDB.getClientData(clientId.id());

        //then
        //scooter jeszcze nie wypozyczony, obecna sesja pusta
        assertNull(clientData.get(CURRENT_RENT_SESSION));

        //when
        ScooterRentalService.rentScooter(clientId, scooterId, testDB);
        final var expectedRentSession = RentSession.createSession(clientId, scooterId);
        final var actualRentSessionAfterRental = (RentSession) clientData.get(CURRENT_RENT_SESSION);

        //then
        //scooter wypozyczony
        assertEquals(expectedRentSession.clientId(), actualRentSessionAfterRental.clientId());
        assertEquals(expectedRentSession.scooterId(), actualRentSessionAfterRental.scooterId());
    }
}