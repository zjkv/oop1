package company;

import company.loyality.TransactionCounter;
import company.repository.TestDB;

import java.util.HashMap;

import static company.repository.TestDB.*;

public class Client {
    final ClientId clientId;

    final float clientCredit;

    final boolean clientWithImmediatePayment;

    final Subscription subscription;

    final TransactionCounter immediateTransactionsCounter;

//    final RideAmount rideAmount;


    public Client(ClientId clientId) {

        TestDB testDB = new TestDB();
        HashMap<Long, HashMap<String, Object>> database = testDB.getDb();
        this.clientId = clientId;
        HashMap clientData = database.get(clientId.id());
        this.clientCredit = (float) clientData.get(CLIENT_CREDIT);
        this.clientWithImmediatePayment = (boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT);
        this.immediateTransactionsCounter = new TransactionCounter((int) clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));
        this.subscription = (Subscription) clientData.get(SUBSCRIPTION);
    }

    public void immediateTransactionsIncrease() {
        immediateTransactionsCounter.increment();
    }
    public ClientId getClientId() {
        return clientId;
    }

    public float getClientCredit() {
        return clientCredit;
    }

    public boolean isClientWithImmediatePayment() {
        return clientWithImmediatePayment;
    }

    public int getImmediateTransactionsCounter() {
        return immediateTransactionsCounter.getCounter();
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
