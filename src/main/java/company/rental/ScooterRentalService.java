package company.rental;

import company.Client;
import company.ClientId;
import company.loyality.Loyalty;
import company.maintanace.Position;
import company.repository.TestDB;

import java.util.HashMap;

import static company.rental.RentSession.calculateUsageTimeFromRentSession;
import static company.repository.TestDB.*;

public class ScooterRentalService {

    private final TestDB testDB;

    public ScooterRentalService(TestDB testDB) {
        this.testDB = testDB;
    }

    public void rentScooter(ClientId clientId, ScooterId scooterId) {
        final var rentSession = RentSession.createSession(clientId, scooterId);

        HashMap<String, Object> clientData = testDB.getClientData(clientId);
        clientData.put(CURRENT_RENT_SESSION, rentSession);
        testDB.storeClientData(clientId, clientData);
    }

    public void returnScooter(ClientId clientId, ScooterId scooterId, Position position) {
        Client client = new Client(clientId, testDB);
        Scooter scooter = new Scooter(scooterId, testDB);

        final var currentRentSession = client.getCurrentRentSession();
        final var closedCurrentRentSession = RentSession.closeSession(currentRentSession);

        final var usageTime = calculateUsageTimeFromRentSession(closedCurrentRentSession);
        Price price = PriceCalculator.calculate(
                scooter.scooterData,
                client.isClientWithImmediatePayment(),
                usageTime,
                client.getPriceAmountClientMultiplicationFactor(),
                client.getSubscription(),
                client.getRidesAmountForCurrentMonth()
        );

        chargeClient(clientId, price.amount());
        client.immediateTransactionsIncrease();

        //save
        HashMap<String, Object> clientData = testDB.getClientData(clientId);
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, client.getImmediateTransactionsCounter().getCounter());
        clientData.put(LOYALTY_POINTS, Loyalty.calculate(usageTime, client.getPriceAmountClientMultiplicationFactor(), price.amount(), client.getSubscription()));
        clientData.put(CHARGE_AMOUNT, price.amount());
        addRentSessionToData(closedCurrentRentSession, clientData);
        clientData.put(CURRENT_RENT_SESSION, null);
        testDB.storeClientData(clientId, clientData);
    }

    private static void chargeClient(ClientId clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }
}
// SZKIELET dla tworzenia listy do wyliczania price factoringu w ujeciu miesiecznym


