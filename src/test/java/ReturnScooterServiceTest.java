import model.ClientId;
import repository.TestDB;
import service.ReturnScooterService;
import model.ScooterId;
import org.junit.jupiter.api.Test;
import repository.Database;
import service.TransactionCounter;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static repository.TestDB.CHARGE_AMOUNT;
import static repository.TestDB.IMMEDIATE_TRANSACTIONS_COUNTER;
import static repository.TestDB.LOYALTY_POINTS;
import static repository.TestDB.NEEDS_TO_CHARGE_BATTERY;

class ReturnScooterServiceTest extends BaseTest  {

    @Test
    void shouldDo() {
        //given
        ClientId clientId = new ClientId(1L);
        ScooterId scooterId = new ScooterId(100L);
        long longitude = 2L;
        long latitude = 3L;
        int minutes = 15;

        int expectedLoyaltyPoints = 0;
        float expectedChargeAmount = 28f;
        boolean expectedNeedsToChargeBattery = true;
        int expectedTransactionCounter = 33;

        //when
        ReturnScooterService.returnScooter(clientId, scooterId, longitude, latitude, minutes, testDB); //I put there ref to test Db just to use only one. xD It is lets say quick fix


        //then
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        assertEquals(expectedLoyaltyPoints, clientData.get(LOYALTY_POINTS));
        assertEquals(expectedChargeAmount, clientData.get(CHARGE_AMOUNT));
        assertEquals(expectedTransactionCounter, clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));
       // assertEquals(expectedNeedsToChargeBattery, clientData.get(NEEDS_TO_CHARGE_BATTERY)); //not set up

    }

}