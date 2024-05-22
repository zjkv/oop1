package company;

public enum Subscription {

    ZERO_TO_NINE(0, 9, 1f),
    TEN_TO_NINETEEN(10, 19, 0.9f),
    TWENTY_TO_TWENTY_NINE(20, 29, 0.8f),
    OVER_THIRTY(30, 999999, 0.75f);

    private final int minRides;
    private final int maxRides;
    private final float discountMultiplier;

    Subscription(int minRides, int maxRides, float discountMultiplier) {
        this.minRides = minRides;
        this.maxRides = maxRides;
        this.discountMultiplier = discountMultiplier;
    }

    public float getDiscountMultiplier() {
        return discountMultiplier;
    }

    public static boolean isRidesAmountInSubscriptionRange(int ridesAmount, Subscription subscription) {
        return ridesAmount >= subscription.minRides && ridesAmount <= subscription.maxRides;
    }
}
