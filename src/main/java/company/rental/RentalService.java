package company.rental;

import company.ClientId;
import company.rental.session.SessionService;
import company.repository.TestDB;

public class RentalService {
    private final TestDB testDB;

    private final SessionService sessionService;

    private final FactorService factorService;

    public RentalService(SessionService sessionService, TestDB testDB, FactorService factorService) {
        this.sessionService = sessionService;
        this.testDB = testDB;
        this.factorService = factorService;
    }


    //probably not needed
    public void rentScooter(ClientId clientId, ScooterId scooterId) {

        var activeSessionsNumber = sessionService.getActiveClientSessionsNumber(clientId);
        var priceFactor = factorService.calculateFactor(activeSessionsNumber);

        sessionService.createSession(clientId, scooterId, priceFactor);
    }

}


