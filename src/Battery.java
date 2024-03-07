public class Battery {

    boolean needsToChargeBattery = false;

    Battery(float batteryLevel) {
        if (batteryLevel < 0.07) {
            needsToChargeBattery = true;
        }
    }
}
