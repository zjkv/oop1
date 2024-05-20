package company.rental;

import java.time.Duration;
import java.time.LocalDateTime;

public record UsageTime(Long minutes) {
    public UsageTime {
        if (minutes == null) {
            throw new RuntimeException("Usage time cant be null");
        }

        if (minutes < 0) {
            throw new RuntimeException("Usage time cannot be les than 0");
        }
    }

    public static UsageTime from(LocalDateTime startTime, LocalDateTime endTime) {
        long seconds = Duration.between(startTime, endTime).toSeconds();
        return new UsageTime(Math.ceilDiv(seconds, 60)); //rounding to minutes UP
    }
}
