package company.repository;

import company.loyality.TransactionCounter;

import java.util.HashMap;

public class Database {

    private HashMap<String, Object> data = new HashMap<>();

    public Database() {
        // client data
        data.put("clientId", 345);
        data.put("clientCredit", 123.23f);
        data.put("clientWithImmediatePayment", true);
        data.put("immediateTransactionsCounter", 32);

        // scooter data
        data.put("scooterId", 456215);
        data.put("batteryLevel", 32.3f);
        data.put("scooterData", new Object[]{"not_fast", 0.0f, 0.1f, 0.2f, 0.3f});
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public static Database saveInDatabase(int loyaltyPoints, float chargeAmount, boolean needsToChargeBattery, TransactionCounter immediateTransactionsCounter) {
        var database = new Database();
        var data = database.getData();
        data.put(TestDB.LOYALTY_POINTS, loyaltyPoints);
        data.put(TestDB.CHARGE_AMOUNT, chargeAmount);
        data.put(TestDB.NEEDS_TO_CHARGE_BATTERY, needsToChargeBattery);
        data.put(TestDB.IMMEDIATE_TRANSACTIONS_COUNTER, immediateTransactionsCounter);

        return database;
    }
}
