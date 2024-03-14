import java.util.HashMap;

class Database {
    private HashMap<String, Object> data = new HashMap<>();

    Database() {
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
}
