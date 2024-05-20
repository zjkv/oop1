package company.rental.rentGroup;

import company.repository.TestDB;

import java.util.HashMap;


public class TImeBonusCalculator {

    private final static Integer DEFAULT_TIME_BONUS_IN_MIN = 0;

    private final TestDB testDB;

    public TImeBonusCalculator(TestDB testDB) {
        this.testDB = testDB;
    }

    public TimeBonus calculateBonus(int numberOfRents) {
        HashMap<String, Object> timeBonusConfig = testDB.getTimeBonusConfig();
        return new TimeBonus((Integer) timeBonusConfig.getOrDefault(String.valueOf(numberOfRents), DEFAULT_TIME_BONUS_IN_MIN));
    }
}
