package company.rental;

import company.rental.rentGroup.TimeBonus;
import company.rental.session.Session;
import company.repository.TestDB;

class PriceCalculator {
    private final TestDB testDB;

    PriceCalculator(TestDB testDB) {
        this.testDB = testDB;
    }

    //returned value should be value object because price cannot be less then 0
    public static float calculate(Object[] scooterData, Boolean isImmediatePayment, UsageTime minutes, float priceAmountClientMultiplicationFactor) {
        PriceComponents priceComponents = new PriceComponents(scooterData);
        Price price = new Price(priceComponents, isImmediatePayment, priceAmountClientMultiplicationFactor);
        return price.calculate(priceComponents, minutes);
    }

    public RentPrice calculate(Session session) {
        double defaultPrice = testDB.getDefaultPrice();
        return new RentPrice(
                UsageTime.from(session.startTime(), session.endTime()).minutes() * defaultPrice * session.sessionPriceFactor());
    }

    // wylicz standardowa kwote a potem naloz promocje
    public RentPrice calculate(Session session, TimeBonus timeBonus) {
        var defaultPrice = testDB.getDefaultPrice();
        var usageTime = UsageTime.from(session.startTime(), session.endTime()).minutes();
        var timeForCharge = usageTime - timeBonus.minutesBonus();
        if (usageTime < timeBonus.minutesBonus()) {
            return new RentPrice(0d);
        }
        return new RentPrice(timeForCharge
                * defaultPrice
                * session.sessionPriceFactor());
    }
}
