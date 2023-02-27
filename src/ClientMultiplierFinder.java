class ClientMultiplierFinder {

    float find(boolean clientWithImmediatePayment) {
        return clientWithImmediatePayment ? 0.9f : 1;
    }
}
