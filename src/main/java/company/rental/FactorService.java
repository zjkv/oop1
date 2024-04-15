package company.rental;

import company.repository.TestDB;

import java.util.HashMap;

public class FactorService {

    private final static double DEFAULT_SESSION_FACTOR = 1.0;
    private final TestDB testDB;

    public FactorService(TestDB testDB) {
        this.testDB = testDB;
    }

    //Calculate but TBH it is only getting value from DB.
    public PriceFactor calculateFactor(int activeSessionsNumber) {
        HashMap<String, Object> priceConfig = testDB.getPriceConfig();


        return new PriceFactor((Double) priceConfig.getOrDefault(String.valueOf(activeSessionsNumber), DEFAULT_SESSION_FACTOR));
    }
}
