package service;

public class PriceComponents {

    float unlocking = 0.0f;
    float pricePerMinute = 0.0f;
    public PriceComponents(Object[] scooterData) {
        if (scooterData[0].equals("not_fast")) {
            unlocking = (float) scooterData[1];
            pricePerMinute = (float) scooterData[2];
        } else {
            unlocking = (float) scooterData[3];
            pricePerMinute = (float) scooterData[4];
        }
    }


    public float getUnlocking() {
        return unlocking;
    }

    public float getPricePerMinute() {
        return pricePerMinute;
    }

}

