package company.rental;

class Price {
    private final float priceAmountClientMultiplicationFactor;

    PriceComponents priceComponents;

    public Price(PriceComponents priceComponents, boolean clientWithImmediatePayment, float priceAmountClientMultiplicationFactor) {
        this.priceComponents = priceComponents;

        if (!clientWithImmediatePayment) {
            this.priceAmountClientMultiplicationFactor = 1;
        } else {
            this.priceAmountClientMultiplicationFactor = priceAmountClientMultiplicationFactor;
        }
    }

    public float calculate(PriceComponents priceComponents, UsageTime minutes) {
        return priceComponents.getUnlocking() + priceComponents.getPricePerMinute() * minutes.minutes() * priceAmountClientMultiplicationFactor;
    }

    public float getPriceAmountClientMultiplicationFactor() {
        return priceAmountClientMultiplicationFactor;
    }

    public PriceComponents getPriceComponents() {
        return priceComponents;
    }
}
