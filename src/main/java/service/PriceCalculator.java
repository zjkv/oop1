package service;

import model.Price;

import static repository.TestDB.CLIENT_WITH_IMMEDIATE_PAYMENT;
import static repository.TestDB.SCOOTER_DATA;

public class PriceCalculator {
    public static float calculate(Object [] scooterData, Boolean isImmediatePayment, int minutes, float priceAmountClientMultiplicationFactor){
        PriceComponents priceComponents = new PriceComponents(scooterData);
        Price price = new Price(priceComponents, isImmediatePayment, priceAmountClientMultiplicationFactor);
        return price.calculate(priceComponents, minutes);
    }
}
