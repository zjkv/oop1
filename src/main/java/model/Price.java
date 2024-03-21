package model;

import service.PriceComponents;

public class Price {
    private float priceAmountClientMultiplicationFactor;

    PriceComponents priceComponents;

    public Price(PriceComponents priceComponents, boolean clientWithImmediatePayment, float priceAmountClientMultiplicationFactor) {
        this.priceComponents = priceComponents;
        this.priceAmountClientMultiplicationFactor = priceAmountClientMultiplicationFactor;

        if (!clientWithImmediatePayment) {
            priceAmountClientMultiplicationFactor = 1;
        }
    }

    public float calculate(PriceComponents priceComponents, int minutes) {
        return priceComponents.getUnlocking() + priceComponents.getPricePerMinute() * minutes * priceAmountClientMultiplicationFactor;
    }

    public float getPriceAmountClientMultiplicationFactor() {
        return priceAmountClientMultiplicationFactor;
    }

    public PriceComponents getPriceComponents() {
        return priceComponents;
    }
}
