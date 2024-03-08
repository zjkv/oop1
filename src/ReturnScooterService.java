import java.util.HashMap;

class ReturnScooterService {
    void returnScooter(Long clientId, Long scooterId, Position where, int minutes) {
        //metoda returnScooter ma 4 parametry - clientId, scooterId, where, minutes
        //resztę pobieramy na podstawię clientId i scooterId z bazy
        //(batteryLevel, Object[] scooterData, float clientCredit, boolean clientWithImmediatePayment, int immediateTransactionsCounter)
        //kod celowo nie jest najpiękniejszy

        Client client = new Client(clientId);
        Scooter scooter = new Scooter(scooterId);

        PriceComponents priceComponents = new PriceComponents(scooter.scooterData);
        Price price = new Price(priceComponents, client.clientWithImmediatePayment);

        float chargeAmount = Math.max(price.calculate(priceComponents, minutes) - client.clientCredit, 0);
        chargeClient(client.clientId, chargeAmount);
        client.immediateTransactionsIncrease();

        saveInDatabase(
                Loyalty.calculate(minutes, price.priceAmountClientMultiplicationFactor, chargeAmount),
                chargeAmount,
                scooter.needsToChargeBattery(),
                client.immediateTransactionsCounter
        );
    }

    private void saveInDatabase(int loyaltyPoints, float chargeAmount, boolean needsToChargeBattery, int immediateTransactionsCounter) {
        //zapis wszystkigo do bazy danych
    }

    private void chargeClient(Long clientId, float chargeAmount) {
        //obciążenie karty kredytowej
    }

}

class Position {
    private float latitude;
    private float longitude;
}
