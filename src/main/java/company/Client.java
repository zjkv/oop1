package company;

import company.loyality.TransactionCounter;
import company.rental.RentSession;
import company.rental.RidesAmount;
import company.repository.TestDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static company.repository.TestDB.*;

public class Client {
    final ClientId clientId;
    final TestDB testDB;
    final float clientCredit;
    final boolean clientWithImmediatePayment;
    final float priceAmountClientMultiplicationFactor;
    final Subscription subscription;
    final TransactionCounter immediateTransactionsCounter;
    final RentSession currentRentSession;
    final ArrayList<RentSession> finishedRentSessions;

    public Client(ClientId clientId, TestDB testDB) {
        this.clientId = clientId;
        this.testDB = testDB;
        HashMap<String, Object> clientData = testDB.getClientData(clientId);
        this.clientCredit = (float) clientData.get(CLIENT_CREDIT);
        this.clientWithImmediatePayment = (boolean) clientData.get(CLIENT_WITH_IMMEDIATE_PAYMENT);
        this.immediateTransactionsCounter = new TransactionCounter((int) clientData.get(IMMEDIATE_TRANSACTIONS_COUNTER));
        this.priceAmountClientMultiplicationFactor = (float) clientData.get(PRICE_AMOUNT_CLIENT_MULTIPLICATION_FACTOR);
        this.subscription = (Subscription) clientData.get(SUBSCRIPTION);
        this.currentRentSession = (RentSession) clientData.get(CURRENT_RENT_SESSION);
        this.finishedRentSessions = (ArrayList<RentSession>) clientData.get(FINISHED_SESSIONS);
    }

    public void immediateTransactionsIncrease() {
        immediateTransactionsCounter.increment();
    }

    public boolean isClientWithImmediatePayment() {
        return clientWithImmediatePayment;
    }

    public TransactionCounter getImmediateTransactionsCounter() {
        return immediateTransactionsCounter;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public float getPriceAmountClientMultiplicationFactor() {
        return priceAmountClientMultiplicationFactor;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public RentSession getCurrentRentSession() {
        return currentRentSession;
    }

    public RidesAmount getFinishedRentSessions() {
        return new RidesAmount(finishedRentSessions.size());
    }

    public RidesAmount getRidesAmountForCurrentMonth() {
        return new RidesAmount(getFinishedRentSessionsForCurrentMonth().size());
    }

    public List<RentSession> getFinishedRentSessionsForCurrentMonth() {
        final var currentMonth = LocalDateTime.now().getMonth();
        return finishedRentSessions.stream().filter(rentSession -> rentSession.endTime().getMonth() == currentMonth).toList();
    }

    // getFinishedRentSessionForGivenMonth() - zaimplementuj


// przeplywy metod i sprawdzenie przeplywow przez testy
}
