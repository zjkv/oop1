package company.rental;

class PriceCalculator {

    //returned value should be value object because price cannot be less then 0
    public static float calculate(Object[] scooterData, Boolean isImmediatePayment, UsageTime minutes, float priceAmountClientMultiplicationFactor) {
        PriceComponents priceComponents = new PriceComponents(scooterData);
        Price price = new Price(priceComponents, isImmediatePayment, priceAmountClientMultiplicationFactor);
        return price.calculate(priceComponents, minutes);
    }
}
