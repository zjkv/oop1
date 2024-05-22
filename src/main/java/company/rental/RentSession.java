package company.rental;

import company.ClientId;

import java.time.Duration;
import java.time.LocalDateTime;

public record RentSession(ClientId clientId, ScooterId scooterId, LocalDateTime startTime, LocalDateTime endTime) {

    public static RentSession createSession(ClientId clientId, ScooterId scooterId) {
        return new RentSession(clientId, scooterId, LocalDateTime.now(), null);
    }

    public static RentSession closeSession(RentSession session) {
        if (session != null) {
            return new RentSession(session.clientId, session.scooterId, session.startTime, LocalDateTime.now());
        } else {
            throw new RuntimeException("No active rent session to be closed");
        }
    }

    public static UsageTime calculateUsageTimeFromRentSession(RentSession rentSession) {
        return new UsageTime((int) Duration.between(rentSession.startTime(), rentSession.endTime()).toMinutes());
    }
}
