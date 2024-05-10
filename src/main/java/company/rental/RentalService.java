package company.rental;

import company.ClientId;
import company.rental.session.SessionService;
import company.repository.TestDB;

public class RentalService {
    private final TestDB testDB;

    private final SessionService sessionService;

    private final FactorService factorService;

    private final PriceCalculator priceCalculator;

    public RentalService(SessionService sessionService, TestDB testDB, FactorService factorService, PriceCalculator priceCalculator) {
        this.sessionService = sessionService;
        this.testDB = testDB;
        this.factorService = factorService;
        this.priceCalculator = priceCalculator;
    }


    //probably not needed
    public void rentScooter(ClientId clientId, ScooterId scooterId) {

        var activeSessionsNumber = sessionService.getActiveClientSessionsNumber(clientId);
        var priceFactor = factorService.calculateFactor(activeSessionsNumber);

        sessionService.createSession(clientId, scooterId, priceFactor);
    }

    //temporary it returns price.
    public RentPrice returnScooter(ClientId clientId, ScooterId scooterId) {
        var closedSession = sessionService.closeSession(clientId, scooterId);

        ///charger service should take this value
        return priceCalculator.calculate(closedSession);
    }

}


