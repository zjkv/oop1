package company.repository;

import company.ClientId;
import company.rental.RentSession;
import company.rental.ScooterId;

import java.util.ArrayList;
import java.util.HashMap;

import static company.Subscription.TEN_TO_NINETEEN;
import static java.time.LocalDateTime.of;

//Generic db, Let say it is nosql db. Yoiu can get object data by its id
public class TestDB {
    public static final String CLIENT_ID = "clientId";
    public static final String SCOOTER_DATA = "scooterData";
    public static final String CLIENT_WITH_IMMEDIATE_PAYMENT = "clientWithImmediatePayment";
    public static final String CLIENT_CREDIT = "clientCredit";
    public static final String IMMEDIATE_TRANSACTIONS_COUNTER = "immediateTransactionsCounter";
    public static final String SUBSCRIPTION = "subscription";
    public static final String CURRENT_RENT_SESSION = "current_rent_session";
    public static final String FINISHED_SESSIONS = "finished_sessions";

    public static final String SCOOTER_ID = "scooterId";
    public static final String BATTERY_LEVEL = "batteryLevel";

    public static final String LOYALTY_POINTS = "loyaltyPoints";
    public static final String CHARGE_AMOUNT = "chargeAmount";
    public static final String NEEDS_TO_CHARGE_BATTERY = "needsToChargeBattery";
    private HashMap<Long, HashMap<String, Object>> db = new HashMap<>();

    public TestDB(){
        this.setUp();
    }

    public TestDB setUp() {
        //ids
        var clientId = 1L;
        var scooterId = 100L;

        // client data
        HashMap<String, Object> clientData = new HashMap<>();
        clientData.put(CLIENT_ID, clientId);
        clientData.put(CLIENT_CREDIT, 123.23f);
        clientData.put(CLIENT_WITH_IMMEDIATE_PAYMENT, true);
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, 32);
        clientData.put(SUBSCRIPTION, TEN_TO_NINETEEN);

        final var finishedSessions = new ArrayList<RentSession>();
        finishedSessions.add(
                new RentSession(new ClientId(clientId), new ScooterId(scooterId), of(2024, 5, 19, 18, 40), of(2024, 5, 19, 18, 50)));
        finishedSessions.add(
                new RentSession(new ClientId(clientId), new ScooterId(scooterId), of(2024, 5, 19, 18, 55), of(2024, 5, 19, 19, 05)));
        clientData.put(FINISHED_SESSIONS, finishedSessions);

        // load client data db
        db.put(clientId, clientData);

        // scooter data
        HashMap<String, Object> scooterData = new HashMap<>();
        scooterData.put(SCOOTER_ID, 100L);
        scooterData.put(BATTERY_LEVEL, 32.3f);
        scooterData.put(SCOOTER_DATA, new Object[]{"not_fast", 1.0f, 2.0f, 3.0f, 4.0f});

        // load sco0ter data db
        db.put(scooterId, scooterData);

        return this;
    }

    public HashMap<Long, HashMap<String, Object>> getDb() {
        return db;
    }

    public HashMap<String, Object> getClientData(Long clientId){
        //check if exist etc..
        return getDb().get(clientId);
    }

    public HashMap<String, Object> getScooterData(Long scooterId){
        //check if exist etc..
        return getDb().get(scooterId);
    }

    public int getFinishedRentSessionsAmount(HashMap<String, Object> clientData) {
        var finishedSessions = (ArrayList<RentSession>) clientData.get(FINISHED_SESSIONS);
        return finishedSessions.size();
    }

    public static void addRentSessionToData(RentSession rentSession, HashMap<String, Object> clientData) {
        var finishedRides = (ArrayList<RentSession>) clientData.get(FINISHED_SESSIONS);
        finishedRides.add(rentSession);
        clientData.put(FINISHED_SESSIONS, finishedRides);
    }

    public HashMap<String, Object> storeClientData(Long clientId, HashMap<String, Object> data){
        //update data, ofc we can do here validation etc.
        return db.put(clientId, data);
    }
}