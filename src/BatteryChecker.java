class BatteryChecker {

    boolean check(float batteryLevel) {
        boolean needsToChargeBattery = false;
        if (batteryLevel < 0.07) {
            needsToChargeBattery = true;
        }
        return needsToChargeBattery;
    }
}
