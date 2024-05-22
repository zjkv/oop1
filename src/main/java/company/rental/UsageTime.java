package company.rental;

import java.time.Duration;

public record UsageTime(Integer minutes) {
    public UsageTime {
        if (minutes == null) {
            throw new RuntimeException("Usage time cant be null");
        }

        if (minutes < 0) {
            throw new RuntimeException("Usage time cannot be less than 0");
        }
    }

    public static UsageTime calculateUsageTimeFromRentSession(RentSession rentSession) {
        return new UsageTime((int) Duration.between(rentSession.startTime(), rentSession.endTime()).toMinutes());
    }
}
