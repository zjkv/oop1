package repository;

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
    private HashMap<Long, HashMap<String, Object>> db = new HashMap<>();

    public TestDB(){
        this.setUp();
    }
    public void setUp() {
        // client data
        HashMap<String, Object> clientData = new HashMap<>();
        var clientId = 1L;

        clientData.put(CLIENT_ID, clientId);
        clientData.put(CLIENT_CREDIT, 123.23f);
        clientData.put(CLIENT_WITH_IMMEDIATE_PAYMENT, true);
        clientData.put(IMMEDIATE_TRANSACTIONS_COUNTER, 32);

        // load client data db
        db.put(clientId, clientData);

        // scooter data
        var scooterId = 100L;
        HashMap<String, Object> scooterData = new HashMap<>();
        scooterData.put(SCOOTER_ID, 100L);
        scooterData.put(BATTERY_LEVEL, 32.3f);
        scooterData.put(SCOOTER_DATA, new Object[]{"not_fast", 1.0f, 2.0f, 3.0f, 4.0f});

        // load sco0ter data db
        db.put(scooterId, scooterData);

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

    public HashMap<String, Object> storeClientData(Long clientId, HashMap<String, Object> data){
        //update data, ofc we can do here validation etc.
        return db.put(clientId, data);
    }
}