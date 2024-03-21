package model;

import repository.Database;
import repository.TestDB;
import service.TransactionCounter;

import java.util.HashMap;

import static repository.TestDB.CLIENT_CREDIT;
import static repository.TestDB.CLIENT_WITH_IMMEDIATE_PAYMENT;
import static repository.TestDB.IMMEDIATE_TRANSACTIONS_COUNTER;

public class Client {
    ClientId clientId;
    float clientCredit;

    boolean clientWithImmediatePayment;

    TransactionCounter immediateTransactionsCounter;

    public Client(ClientId clientId) {
        TestDB testDB = new TestDB();
        HashMap<Long, HashMap<String, Object>> database = testDB.getDb();
        this.clientId = clientId;
        HashMap clientData = database.get(clientId.id());
        this.clientCredit = (float) clientData.get(CLIENT_CREDIT);
        this.clientWithImmediatePayment = (boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT);
        this.immediateTransactionsCounter = new TransactionCounter((int) clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));
    }

/*    public void immediateTransactionsIncrease() {
        new TransactionCounter(immediateTransactionsCounter).increment(); //What is happening here BARTEK. you increment value but not store it anywhere. xD
    }*/

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
}
