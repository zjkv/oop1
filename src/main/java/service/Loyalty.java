package service;

public class Loyalty {
    public static int calculate(int minutes, float priceAmountClientMultiplicationFactor, float chargeAmount) {
        int loyaltyPoints = 0;
        if (minutes > 15 && minutes < 50) {
            loyaltyPoints = 4;
            if (priceAmountClientMultiplicationFactor < 1) {
                loyaltyPoints = 2;
            }
        }

        if (minutes >= 50 && chargeAmount > 30) {
            loyaltyPoints = 20;
        }

        return loyaltyPoints;
    }
}
