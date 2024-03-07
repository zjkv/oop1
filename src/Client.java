public class Client {
    Long clientId;
    float clientCredit;
    boolean clientWithImmediatePayment;

    int immediateTransactionsCounter;

    Client(Long clientId) {
        this.clientId = clientId;
    }

    void immediateTransactionsIncrease() {
        immediateTransactionsCounter++;
    }

}
