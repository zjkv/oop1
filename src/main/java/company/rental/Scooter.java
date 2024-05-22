package company.rental;

import company.maintanace.Battery;
import company.repository.Database;
import company.repository.TestDB;

import java.util.HashMap;

public class Scooter {

    final ScooterId scooterId;
    final TestDB testDB;
    final Object[] scooterData;
    final float batteryLevel;

    public Scooter(ScooterId scooterId, TestDB testDB) {
        this.scooterId = scooterId;
        this.testDB = testDB;
        HashMap<String, Object> scooterData = testDB.getScooterData(scooterId);
        this.scooterData = (Object[]) scooterData.get("scooterData");
        this.batteryLevel = (float) scooterData.get("batteryLevel");
    }

    public boolean needsToChargeBattery() {
        return new Battery(batteryLevel).isNeedsToChargeBattery();
    }

    public ScooterId getScooterId() {
        return scooterId;
    }

    public Object[] getScooterData() {
        return scooterData;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }
}
