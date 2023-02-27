class ClientCharger {

    private int immediateTransactionsCounter;

    int getImmediateTransactionsCounter() {
        return immediateTransactionsCounter;
    }

    float charge(Long clientId, int minutes, float clientCredit, boolean clientWithImmediatePayment, int immediateTransactionsCounter, float[] prices, float priceAmountClientMultiplicationFactor) {
        float price = prices[0] + prices[1] * minutes * priceAmountClientMultiplicationFactor;
        float chargeAmount = Math.max(price - clientCredit, 0);
        chargeClient(clientId, chargeAmount);
        if (clientWithImmediatePayment) {
            this.immediateTransactionsCounter++;
        }
        return chargeAmount;
    }

    private void chargeClient(Long clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }
}
