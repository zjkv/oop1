import java.util.HashMap;

public class Scooter {

    ScooterId scooterId;
    Object[] scooterData;

    float batteryLevel;

    Scooter(ScooterId scooterId) {
        HashMap<String, Object> database = new Database().getData();

        this.scooterId = scooterId;
        this.scooterData = (Object[]) database.get("scooterData");
        this.batteryLevel = (float) database.get("batteryLevel");
    }

    boolean needsToChargeBattery() {
        return new Battery(batteryLevel).needsToChargeBattery;
    }
}
