package company.rental;

import company.BaseTest;
import company.ClientId;
import company.rental.session.Session;
import company.rental.session.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RentalServiceTestIT extends BaseTest {

    private final SessionService sessionService;
    private final FactorService factorService;

    private final RentalService rentalService;

    private final PriceCalculator priceCalculator;

    ClientId clientId = new ClientId(1L);
    ScooterId scooterId1 = new ScooterId(101L);
    ScooterId scooterId2 = new ScooterId(102L);
    ScooterId scooterId3 = new ScooterId(103L);
    ScooterId scooterId4 = new ScooterId(104L);

    ScooterId scooterId5 = new ScooterId(105L);

    RentalServiceTestIT() {
        this.sessionService = new SessionService(testDB);
        this.factorService = new FactorService(testDB);
        this.priceCalculator = new PriceCalculator(testDB);
        this.rentalService = new RentalService(sessionService, testDB, factorService, priceCalculator);
    }

    @Test
    void shouldCreate4ActiveSessionsClose2AndRecreate3WithCorrectPriceFactor() {

        //create 4 session
        rentalService.rentScooter(clientId, scooterId1);
        rentalService.rentScooter(clientId, scooterId2);
        rentalService.rentScooter(clientId, scooterId3);
        rentalService.rentScooter(clientId, scooterId4);

        //check factors
        Session firstSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId1);
        Session secondSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId2);
        Session thirdSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId3);
        Session forthSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId4);

        assertEquals(4, testDB.getClientActiveSession(clientId.id()).size());

        //checking pricing

        //NUMBER OF USERS / PRICE FACTOR
/*        priceConfigData.put("3", 0.95);
        priceConfigData.put("4", 0.94);
        priceConfigData.put("5", 0.915);
        priceConfigData.put("6", 0.9);*/
        assertEquals(1d, firstSession.sessionPriceFactor());
        assertEquals(1d, secondSession.sessionPriceFactor());
        assertEquals(0.95, thirdSession.sessionPriceFactor());
        assertEquals(0.94, forthSession.sessionPriceFactor());

        //close 2
        rentalService.returnScooter(clientId, scooterId1);
        rentalService.returnScooter(clientId, scooterId3);

        //create 3 new sessions
        rentalService.rentScooter(clientId, scooterId5);
        rentalService.rentScooter(clientId, scooterId1);
        rentalService.rentScooter(clientId, scooterId3);


        Session thirdSession_2 = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId5);
        Session forthSession_2 = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId1);
        Session fiftSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId3);

        //checking pricing
        assertEquals(0.95, thirdSession_2.sessionPriceFactor());
        assertEquals(0.94, forthSession_2.sessionPriceFactor());
        assertEquals(0.915, fiftSession.sessionPriceFactor());

        assertEquals(5, testDB.getClientActiveSession(clientId.id()).size());
    }


    @Test
    void shouldCalculateCorrectPricesForFewSessions() throws InterruptedException {

        //create 4 session
        rentalService.rentScooter(clientId, scooterId1);
        rentalService.rentScooter(clientId, scooterId2);
        rentalService.rentScooter(clientId, scooterId3);
        rentalService.rentScooter(clientId, scooterId4);

        //checking pricing

        //NUMBER OF USERS / PRICE FACTOR
/*        priceConfigData.put("3", 0.95);
        priceConfigData.put("4", 0.94);
        priceConfigData.put("5", 0.915);
        priceConfigData.put("6", 0.9);*/

        Thread.sleep(2000); //test are too fast
        //close 2
        RentPrice firstSessionClosed_Pricing_1_0 = rentalService.returnScooter(clientId, scooterId1);
        RentPrice thirdSessionClosed_Pricing_0_95 = rentalService.returnScooter(clientId, scooterId3);

        //check price after 1 less than one minute (rounding to 1 minute)
        assertEquals(new BigDecimal("10.00"), firstSessionClosed_Pricing_1_0.getPrice());
        assertEquals(new BigDecimal("9.50"), thirdSessionClosed_Pricing_0_95.getPrice());


        Thread.sleep(61000); //sleep for 1 more minute

        //create 3 new sessions
        rentalService.rentScooter(clientId, scooterId5);
        rentalService.rentScooter(clientId, scooterId1);
        rentalService.rentScooter(clientId, scooterId3);
        Thread.sleep(2000); //test are too fast


        Session thirdSession_2 = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId5);
        Session forthSession_2 = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId1);
        Session fiftSession = testDB.getActiveSessionByClientAndScooterId(clientId, scooterId3);

        //checking pricing
        assertEquals(0.95, thirdSession_2.sessionPriceFactor());
        assertEquals(0.94, forthSession_2.sessionPriceFactor());
        assertEquals(0.915, fiftSession.sessionPriceFactor());

        //NUMBER OF USERS / PRICE FACTOR
/*        priceConfigData.put("3", 0.95);
        priceConfigData.put("4", 0.94);
        priceConfigData.put("5", 0.915);
        priceConfigData.put("6", 0.9);*/


        RentPrice secondSessionClosed_Pricing_1_0 = rentalService.returnScooter(clientId, scooterId2);
        RentPrice forthSessionClosed_Pricing_0_94 = rentalService.returnScooter(clientId, scooterId4);

        assertEquals(new BigDecimal("20.00"), secondSessionClosed_Pricing_1_0.getPrice());
        assertEquals(new BigDecimal("18.80"), forthSessionClosed_Pricing_0_94.getPrice());


        RentPrice fiveClosedSession_Pricing_0_95 = rentalService.returnScooter(clientId, scooterId5);
        RentPrice sixClosedSession_Pricing_0_94 = rentalService.returnScooter(clientId, scooterId1);
        RentPrice sevenClosedSession_Pricing_0_915 = rentalService.returnScooter(clientId, scooterId3);

        //check pricing after one minuter but round to 2 minutes
        assertEquals(new BigDecimal("20.00"), secondSessionClosed_Pricing_1_0.getPrice());
        assertEquals(new BigDecimal("18.80"), forthSessionClosed_Pricing_0_94.getPrice());

        //checking for 2 minutes
        assertEquals(new BigDecimal("9.50"), fiveClosedSession_Pricing_0_95.getPrice());
        assertEquals(new BigDecimal("9.40"), sixClosedSession_Pricing_0_94.getPrice());
        assertEquals(new BigDecimal("9.15"), sevenClosedSession_Pricing_0_915.getPrice());

        assertEquals(5, testDB.getClientActiveSession(clientId.id()).size());
    }
}