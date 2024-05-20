package company.rental.session;

import company.ClientId;
import company.rental.ScooterId;

import java.time.LocalDateTime;

//OFC we can add some session ID, but is not needed. Key here is scooterId && client Id combination which should be uniq
public record Session(ScooterId scooterId, LocalDateTime startTime, LocalDateTime endTime, ClientId clientId,
                      Double sessionPriceFactor) {

    public static Session createSession(ScooterId scooterId, ClientId clientId, Double sessionPriceFactor) {
        return new Session(scooterId, LocalDateTime.now(), null, clientId, sessionPriceFactor);
    }

    public static Session closeSession(Session session) {
        return new Session(session.scooterId, session.startTime, LocalDateTime.now(), session.clientId, session.sessionPriceFactor);
    }

}
