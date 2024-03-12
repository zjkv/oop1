public class Price {
    float priceAmountClientMultiplicationFactor = 0.9f;

    PriceComponents priceComponents;

    Price(PriceComponents priceComponents, boolean clientWithImmediatePayment) {
        this.priceComponents = priceComponents;

        if (!clientWithImmediatePayment) {
            priceAmountClientMultiplicationFactor = 1;
        }
    }

    float calculate(PriceComponents priceComponents, int minutes) {
        return priceComponents.unlocking + priceComponents.pricePerMinute * minutes * priceAmountClientMultiplicationFactor;
    }
}
