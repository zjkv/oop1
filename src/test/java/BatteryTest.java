import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BatteryTest {

    @Test
    void shouldSetNeedsToChargeBatteryAccordingToBatteryLevelValue() {
        //given
        float batteryLevelWhichNeedToBeCharged = 0.05f;
        float batteryLevelWhichNotNeedToBeCharged = 0.08f;

        //when
        Battery batteryWhichNeedsToBeCharged = new Battery(batteryLevelWhichNeedToBeCharged);
        Battery batteryWhichNotNeedsToBeCharged = new Battery(batteryLevelWhichNotNeedToBeCharged);

        //then
        Assertions.assertTrue(batteryWhichNeedsToBeCharged.needsToChargeBattery);
        Assertions.assertFalse(batteryWhichNotNeedsToBeCharged.needsToChargeBattery);
    }
}