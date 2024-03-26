package company.rental;

import company.BaseTest;
import company.ClientId;
import company.maintanace.Latitude;
import company.maintanace.Longitude;
import company.maintanace.Position;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static company.repository.TestDB.CHARGE_AMOUNT;
import static company.repository.TestDB.IMMEDIATE_TRANSACTIONS_COUNTER;
import static company.repository.TestDB.LOYALTY_POINTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReturnScooterServiceTest extends BaseTest {

    @Test
    void shouldDo() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        Longitude longitude = new Longitude(15F);
        Latitude latitude = new Latitude(50F);
        UsageTime minutes = new UsageTime(15);

        Position position = new Position(latitude, longitude); //Position Value object

        int expectedLoyaltyPoints = 0;
        float expectedChargeAmount = 28f;
        boolean expectedNeedsToChargeBattery = true;
        int expectedTransactionCounter = 33;

        //when
        ReturnScooterService.returnScooter(clientId, scooterId, position, minutes, testDB); //I put there ref to test Db just to use only one. xD It is lets say quick fix


        //then
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        assertEquals(expectedLoyaltyPoints, clientData.get(LOYALTY_POINTS));
        assertEquals(expectedChargeAmount, clientData.get(CHARGE_AMOUNT));
        assertEquals(expectedTransactionCounter, clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));
        // assertEquals(expectedNeedsToChargeBattery, clientData.get(NEEDS_TO_CHARGE_BATTERY)); //not set up

    }

    @Test
    void shouldThrowExceptionWhenCordsNotFromPoland() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        Longitude longitude = new Longitude(2F); //Cords not from poland
        Latitude latitude = new Latitude(3F);
        UsageTime minutes = new UsageTime(15);

        //line bellow thorw exception
        Position position = new Position(latitude, longitude); //Position Value object

        //when
        ReturnScooterService.returnScooter(clientId, scooterId, position, minutes, testDB); //I put there ref to test Db just to use only one. xD It is lets say quick fix


        //expected
        assertThrows(RuntimeException.class,
                () -> ReturnScooterService.returnScooter(clientId, scooterId, position, minutes, testDB));
        // assertEquals(expectedNeedsToChargeBattery, clientData.get(NEEDS_TO_CHARGE_BATTERY)); //not set up

    }

}