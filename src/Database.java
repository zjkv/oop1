import java.util.HashMap;

class Database {
    private HashMap<String, Object> data = new HashMap<>();

    Database() {
        // client data
        data.put("clientId", 345);
        data.put("clientCredit", 123.23);
        data.put("clientWithImmediatePayment", true);
        data.put("immediateTransactionsCounter", 32);

        // scooter data
        data.put("scooterId", 456215);
        data.put("batteryLevel", 32.3);
    }
}
