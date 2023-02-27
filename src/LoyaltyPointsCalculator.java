 class LoyaltyPointsCalculator {

    int calculate(int minutes, float clientPriceMultiplier, float chargeAmount) {
        int loyaltyPoints = 0;
        if (minutes > 15 && minutes < 50) {
            loyaltyPoints = 4;
            if (clientPriceMultiplier < 1) {
                loyaltyPoints = 2;
            }
        }

        if (minutes >= 50 && chargeAmount > 30) {
            loyaltyPoints = 20;
        }
        return loyaltyPoints;
    }
}
