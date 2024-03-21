package service;

import model.Client;
import model.ClientId;
import model.Price;
import model.Scooter;
import model.ScooterId;
import repository.Database;
import repository.TestDB;

import java.util.HashMap;

import static repository.TestDB.CHARGE_AMOUNT;
import static repository.TestDB.CLIENT_ID;
import static repository.TestDB.CLIENT_WITH_IMMEDIATE_PAYMENT;
import static repository.TestDB.IMMEDIATE_TRANSACTIONS_COUNTER;
import static repository.TestDB.LOYALTY_POINTS;
import static repository.TestDB.SCOOTER_DATA;

public class ReturnScooterService {

    private TestDB testDB = new TestDB();
    public void returnScooter(ClientId clientId, ScooterId scooterId, long latitude, long  longitude, int minutes, TestDB testDB) {
        //metoda returnScooter ma 4 parametry - clientId, scooterId, where, minutes
        //resztę pobieramy na podstawię clientId i scooterId z bazy
        //(batteryLevel, Object[] scooterData, float clientCredit, boolean clientWithImmediatePayment, int immediateTransactionsCounter)
        //kod celowo nie jest najpiękniejszy


        //Client client = new Client(clientId);
        //Scooter scooter = new Scooter(scooterId);

        //hashmap should be replaced by the DB object
        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        HashMap<String, Object> scooterData = testDB.getScooterData(scooterId.id());

        float priceAmountClientMultiplicationFactor = 0.9f;  //this can be takes from db eg from Client props, different clients differt multifactors


        float chargeAmount = PriceCalculator.calculate((Object[]) scooterData.get(SCOOTER_DATA), (Boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT), minutes, priceAmountClientMultiplicationFactor);

        chargeClient(clientId, chargeAmount);

        Client client = new Client(clientId); //this should be get from db, in place of 31 line
        client.immediateTransactionsIncrease();
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, client.getImmediateTransactionsCounter());
        clientData.put(LOYALTY_POINTS, Loyalty.calculate(minutes, priceAmountClientMultiplicationFactor, chargeAmount));
        clientData.put(CHARGE_AMOUNT, chargeAmount);
        testDB.updateClientData(clientId.id(), clientData);
/*
        Database.saveInDatabase(
                Loyalty.calculate(minutes, price.getPriceAmountClientMultiplicationFactor(), chargeAmount),
                chargeAmount,
                scooter.needsToChargeBattery(),
                new TransactionCounter(client.getImmediateTransactionsCounter())
        );*/
    }

    private void chargeClient(ClientId clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }

}

class Position {
    private float latitude;
    private float longitude;
}
