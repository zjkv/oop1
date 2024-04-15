package company.repository;

import company.ClientId;
import company.rental.session.Session;

import java.util.ArrayList;
import java.util.HashMap;

//Generic db, Let say it is nosql db. Yoiu can get object data by its id
public class TestDB {
    public static final String CLIENT_ID = "clientId";
    public static final String SCOOTER_DATA = "scooterData";
    public static final String CLIENT_WITH_IMMEDIATE_PAYMENT = "clientWithImmediatePayment";
    public static final String CLIENT_CREDIT = "clientCredit";
    public static final String IMMEDIATE_TRANSACTIONS_COUNTER = "immediateTransactionsCounter";
    public static final String SCOOTER_ID = "scooterId";
    public static final String BATTERY_LEVEL = "batteryLevel";

    public static final String LOYALTY_POINTS = "loyaltyPoints";
    public static final String CHARGE_AMOUNT = "chargeAmount";
    public static final String NEEDS_TO_CHARGE_BATTERY = "needsToChargeBattery";
    public static final String ACTIVE_SESSIONS = "activeSessions";

    public static final String SESSION_PRICE_FACTOR = "sessionPriceFactor";

    public static final String CLIENT_LIST = "clientList";
    private HashMap<Long, HashMap<String, Object>> db = new HashMap<>();

    public TestDB() {
        this.setUp();
    }

    public TestDB setUp() {
        // client data
        HashMap<String, Object> clientData = new HashMap<>();
        var clientId = 1L;

        clientData.put(CLIENT_ID, clientId);
        clientData.put(CLIENT_CREDIT, 123.23f);
        clientData.put(CLIENT_WITH_IMMEDIATE_PAYMENT, true);
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, 32);
        clientData.put(ACTIVE_SESSIONS, new ArrayList<Session>());

        // load client data db
        db.put(clientId, clientData);

        // scooter data
        var scooterId = 100L;
        HashMap<String, Object> scooterData = new HashMap<>();
        scooterData.put(SCOOTER_ID, 100L);
        scooterData.put(BATTERY_LEVEL, 32.3f);
        scooterData.put(SCOOTER_DATA, new Object[]{"not_fast", 1.0f, 2.0f, 3.0f, 4.0f});
        scooterData.put(CLIENT_LIST, new ArrayList<ClientId>());

        // load sco0ter data db
        db.put(scooterId, scooterData);


        // price konfig data
        //faktor pricing zależny od liczby osób:
        //przy 3 osobach 5% na dwie pozostałe hulajnogi
        //przy 4 osobach 6% na trzy pozostałe hulajnogi
        //przy 5 osobach 8,5% na cztery pozostałe hulajnogi
        //przy 6 osobach 10% na piec pozostałe hulajnogi
        var priceConfigId = 11111L;
        HashMap<String, Object> priceConfigData = new HashMap<>();
        //NUMBER OF USERS / PRICE FACTOR
        priceConfigData.put("3", 0.95);
        priceConfigData.put("4", 0.94);
        priceConfigData.put("5", 0.915);
        priceConfigData.put("6", 0.9);

        db.put(priceConfigId, priceConfigData);

        return this;
    }

    public HashMap<Long, HashMap<String, Object>> getDb() {
        return db;
    }

    public HashMap<String, Object> getClientData(Long clientId){
        //check if exist etc..
        return getDb().get(clientId);
    }

    public HashMap<String, Object> getScooterData(Long scooterId) {
        //check if exist etc..
        return getDb().getOrDefault(scooterId, new HashMap<>());
    }

    public HashMap<String, Object> storeClientData(Long clientId, HashMap<String, Object> data) {
        //update data, ofc we can do here validation etc.
        return db.put(clientId, data);
    }

    public HashMap<String, Object> storeScooterData(Long scooterId, HashMap<String, Object> data) {
        //update data, ofc we can do here validation etc.
        return db.put(scooterId, data);
    }

    public ArrayList<Session> getClientActiveSession(Long clientId) {
        //check if exist etc..
        return (ArrayList<Session>) getDb().get(clientId).get(ACTIVE_SESSIONS);
    }

    //Hardoced Value
    public HashMap<String, Object> getPriceConfig() {
        return getDb().get(11111L);
    }
}