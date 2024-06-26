package company.loyality;


import company.Subscription;
import company.rental.UsageTime;

public class Loyalty {

    //potencial bad implementation, because scooter service need to use that method. It should be used only in the scope of this package.
    public static int calculate(UsageTime minutes, float priceAmountClientMultiplicationFactor, float chargeAmount, Subscription subscription) {
        int loyaltyPoints = 0;
        if (minutes.minutes() > 15 && minutes.minutes() < 50) {
            loyaltyPoints = 4;
            if (priceAmountClientMultiplicationFactor < 1) {
                loyaltyPoints = 2;
            }
        }

        if (minutes.minutes() >= 50 && chargeAmount > 30) {
            loyaltyPoints = 20;
        }

        if (subscription!=null) {
            loyaltyPoints = Math.round(loyaltyPoints*1.2f);
        }
        return loyaltyPoints;
    }
}
