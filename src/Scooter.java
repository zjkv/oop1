public class Scooter {

    Long scooterId;
    Object[] scooterData;

    float batteryLevel;

    Scooter(Long scooterId) {
        this.scooterId = scooterId;
    }

    boolean needsToChargeBattery() {
        return new Battery(batteryLevel).needsToChargeBattery;
    }
}
