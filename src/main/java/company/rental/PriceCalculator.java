package company.rental;

import company.Subscription;

import static company.Subscription.isRidesAmountInSubscriptionRange;

class PriceCalculator {

    //returned value should be value object because price cannot be less then 0
    public static Price calculate(Object[] scooterData, Boolean isImmediatePayment, UsageTime usageTime, float priceAmountClientMultiplicationFactor, Subscription subscription, RidesAmount ridesAmount) {
        final var priceComponents = new PriceComponents(scooterData);
        final var basePrice = calculateBasePrice(priceComponents, isImmediatePayment, usageTime, priceAmountClientMultiplicationFactor);
        final var subscriptionDiscount = getSubscriptionDiscount(ridesAmount, subscription);
        return new Price(basePrice * subscriptionDiscount);
    }

    private static float calculateBasePrice(PriceComponents priceComponents, Boolean isImmediatePayment, UsageTime usageTime, float priceAmountClientMultiplicationFactor) {
        float resolvePriceFactor = !isImmediatePayment ? 1 : priceAmountClientMultiplicationFactor;
        return priceComponents.getUnlocking() + priceComponents.getPricePerMinute() * usageTime.minutes() * resolvePriceFactor;
    }

    private static float getSubscriptionDiscount(RidesAmount ridesAmount, Subscription subscription) {
        return isRidesAmountInSubscriptionRange(ridesAmount, subscription)
                ? subscription.getDiscountMultiplier()
                : 1f;
    }
}
