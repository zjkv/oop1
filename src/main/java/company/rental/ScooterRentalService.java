package company.rental;

import company.Client;
import company.ClientId;
import company.Subscription;
import company.loyality.Loyalty;
import company.maintanace.Position;
import company.repository.TestDB;

import java.util.HashMap;

import static company.rental.UsageTime.calculateUsageTimeFromRentSession;
import static company.repository.TestDB.*;

public class ScooterRentalService {

    private final TestDB testDB = new TestDB();

    public static void rentScooter(ClientId clientId, ScooterId scooterId, TestDB testDB) {
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());

        final var rentSession = RentSession.createSession(clientId, scooterId);
        clientData.put(CURRENT_RENT_SESSION, rentSession);
        testDB.storeClientData(clientId.id(), clientData);
    }

    public static void returnScooter(ClientId clientId, ScooterId scooterId, Position position, TestDB testDB) {

        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        HashMap<String, Object> scooterData = testDB.getScooterData(scooterId.id());

        final var ridesAmount = testDB.getFinishedRentSessionsAmount(clientData);

        final var currentRentSession = (RentSession) clientData.get(CURRENT_RENT_SESSION);
        final var closedCurrentRentSession = RentSession.closeSession(currentRentSession);

        //I moved UsageTime from method parameters to be calculated by values from current rent session
        final var usageTime = calculateUsageTimeFromRentSession(closedCurrentRentSession);

        Subscription subscription = (Subscription) clientData.get(SUBSCRIPTION);

        float priceAmountClientMultiplicationFactor = 0.9f;  //this can be takes from db eg from Client props, different clients differt multifactors

        float chargeAmount = PriceCalculator.calculate((Object[]) scooterData.get(SCOOTER_DATA), (Boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT), usageTime, priceAmountClientMultiplicationFactor, subscription, ridesAmount);

        chargeClient(clientId, chargeAmount);

        Client client = new Client(clientId); //this should be get from db, in place of 31 line
        client.immediateTransactionsIncrease();
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, client.getImmediateTransactionsCounter());
        clientData.put(LOYALTY_POINTS, Loyalty.calculate(usageTime, priceAmountClientMultiplicationFactor, chargeAmount, subscription));
        clientData.put(CHARGE_AMOUNT, chargeAmount);
        addRentSessionToData(closedCurrentRentSession, clientData);

        //clear current rent session
        clientData.put(CURRENT_RENT_SESSION, null);

        testDB.storeClientData(clientId.id(), clientData);
    }

    private static void chargeClient(ClientId clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }

}

