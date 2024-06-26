package company.rental;

import company.Client;
import company.ClientId;
import company.Subscription;
import company.loyality.Loyalty;
import company.maintanace.Position;
import company.repository.TestDB;

import java.util.HashMap;

import static company.Subscription.isRidesAmountInSubscriptionRange;
import static company.repository.TestDB.*;

public class ReturnScooterService {

    private final TestDB testDB = new TestDB();

    public static void returnScooter(ClientId clientId, ScooterId scooterId, Position position, UsageTime minutes, TestDB testDB) {
        //metoda returnScooter ma 4 parametry - clientId, scooterId, where, minutes
        //resztę pobieramy na podstawię clientId i scooterId z bazy
        //(batteryLevel, Object[] scooterData, float clientCredit, boolean clientWithImmediatePayment, int immediateTransactionsCounter)
        //kod celowo nie jest najpiękniejszy

        int ridesAmount = 4; // tymczasowa deklaracja ilosci dokonanych przejazdow

        //Client client = new Client(clientId);
        //Scooter scooter = new Scooter(scooterId);

        //hashmap should be replaced by the DB object
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        HashMap<String, Object> scooterData = testDB.getScooterData(scooterId.id());

        Subscription subscription = (Subscription) clientData.get(SUBSCRIPTION);

        float priceAmountClientMultiplicationFactor = 0.9f;  //this can be takes from db eg from Client props, different clients differt multifactors

        float subscriptionDiscount = getSubscriptionDiscount(ridesAmount, subscription);

        float chargeAmount = PriceCalculator.calculate((Object[]) scooterData.get(SCOOTER_DATA), (Boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT), minutes, priceAmountClientMultiplicationFactor, subscriptionDiscount);

        chargeClient(clientId, chargeAmount);

        Client client = new Client(clientId); //this should be get from db, in place of 31 line
        client.immediateTransactionsIncrease();
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, client.getImmediateTransactionsCounter());
        clientData.put(LOYALTY_POINTS, Loyalty.calculate(minutes, priceAmountClientMultiplicationFactor, chargeAmount, subscription));
        clientData.put(CHARGE_AMOUNT, chargeAmount);
        testDB.storeClientData(clientId.id(), clientData);
/*
        Database.saveInDatabase(
                Loyalty.calculate(minutes, price.getPriceAmountClientMultiplicationFactor(), chargeAmount),
                chargeAmount,
                scooter.needsToChargeBattery(),
                new TransactionCounter(client.getImmediateTransactionsCounter())
        );*/
    }

    private static float getSubscriptionDiscount(int ridesAmount, Subscription subscription) {
        return isRidesAmountInSubscriptionRange(ridesAmount, subscription)
                ? subscription.getDiscountMultiplier()
                : 1f;
    }
    private static void chargeClient(ClientId clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }

}

