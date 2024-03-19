import java.util.HashMap;

public class Client {
    ClientId clientId;
    float clientCredit;
    boolean clientWithImmediatePayment;

    int immediateTransactionsCounter;

    Client(ClientId clientId) {
        HashMap<String, Object> database = new Database().getData();
        this.clientId = clientId;
        this.clientCredit = (float) database.get("clientCredit");
        this.clientWithImmediatePayment = (boolean) database.get("clientWithImmediatePayment");
        this.immediateTransactionsCounter = (int) database.get("immediateTransactionCounter");
    }

    void immediateTransactionsIncrease() {
        new TransactionCounter(immediateTransactionsCounter).increment();
    }

}
