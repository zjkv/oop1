package company.rental;

import company.Subscription;

import static company.Subscription.isRidesAmountInSubscriptionRange;

class PriceCalculator {

    //returned value should be value object because price cannot be less then 0
    public static float calculate(Object[] scooterData, Boolean isImmediatePayment, UsageTime minutes, float priceAmountClientMultiplicationFactor, Subscription subscription, RidesAmount ridesAmount) {
        PriceComponents priceComponents = new PriceComponents(scooterData);
        Price price = new Price(priceComponents, isImmediatePayment, priceAmountClientMultiplicationFactor);
        float subscriptionDiscount = getSubscriptionDiscount(ridesAmount, subscription);
        return price.calculate(priceComponents, minutes) * subscriptionDiscount;
    }

    private static float getSubscriptionDiscount(RidesAmount ridesAmount, Subscription subscription) {
        return isRidesAmountInSubscriptionRange(ridesAmount, subscription)
                ? subscription.getDiscountMultiplier()
                : 1f;
    }
}
