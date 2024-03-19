import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnScooterServiceTest {

    @Test
    void shouldDo() {
        //given
        ClientId clientId = new ClientId(2L);
        ScooterId scooterId = new ScooterId(3L);
        Position where = new Position();
        int minutes = 2;

        //when
        new ReturnScooterService().returnScooter(clientId, scooterId, where, minutes);
        var database = new Database();

        //
        int expectedLoyaltyPoints = 3;
        float expectedChargeAmount = 3f;
        boolean expectedNeedsToChargeBattery = true;
        TransactionCounter expectedTransactionCounter = new TransactionCounter(5);
        Database expectedDatabaseState = Database.saveInDatabase(expectedLoyaltyPoints, expectedChargeAmount, expectedNeedsToChargeBattery, expectedTransactionCounter);
        assertEquals(expectedDatabaseState, database.getData().get("loyaltyPoints"));
    }

}