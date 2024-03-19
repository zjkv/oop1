public class Battery {

    private static final float LOWEST_NOT_NEED_TO_CHARGE_BATTERY_LEVEL = 0.07f;

    boolean needsToChargeBattery = false;

    Battery(float batteryLevel) {
        if (batteryLevel < LOWEST_NOT_NEED_TO_CHARGE_BATTERY_LEVEL) {
            needsToChargeBattery = true;
        }
    }
}
