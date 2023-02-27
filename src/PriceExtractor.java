class PriceExtractor {

    float[] extract(Object[] scooterData) {
        float[] prices = new float[2];
        if (scooterData[0].equals("not_fast")) {
            prices[0] = (float) scooterData[1];
            prices[1] = (float) scooterData[2];
        } else {
            prices[0] = (float) scooterData[3];
            prices[1] = (float) scooterData[4];
        }
        return prices;
    }
}
