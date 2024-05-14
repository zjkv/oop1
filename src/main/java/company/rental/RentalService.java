package company.rental;

import company.ClientId;
import company.rental.rentGroup.RentGroupId;
import company.rental.rentGroup.TImeBonusCalculator;
import company.rental.session.Session;
import company.rental.session.SessionService;
import company.repository.TestDB;

import java.util.HashMap;

import static company.repository.TestDB.GROUP_MINUTES_BONUS;
import static company.repository.TestDB.GROUP_SESSIONS;

public class RentalService {
    private final TestDB testDB;

    private final SessionService sessionService;

    private final FactorService factorService;

    private final PriceCalculator priceCalculator;

    private final TImeBonusCalculator tImeBonusCalculator;

    public RentalService(SessionService sessionService, TestDB testDB, FactorService factorService, PriceCalculator priceCalculator, TImeBonusCalculator tImeBonusCalculator) {
        this.sessionService = sessionService;
        this.testDB = testDB;
        this.factorService = factorService;
        this.priceCalculator = priceCalculator;
        this.tImeBonusCalculator = tImeBonusCalculator;
    }

    ///zrob test ktory ma 5 skuterow oddaje 3 i wypozycza 1
    public void rentGroupsOfScooters(ClientId clientId, RentGroupId rentGroupId, ScooterId scooterId) {
        var session = rentScooter(clientId, scooterId);
        var rentGroupSessions = testDB.getRentGroupSessions(rentGroupId.id());
        rentGroupSessions.add(session);
        var timeBonus = tImeBonusCalculator.calculateBonus(rentGroupSessions.size());

        HashMap<String, Object> rentGroupData = new HashMap<>();
        rentGroupData.put(GROUP_SESSIONS, rentGroupSessions);
        rentGroupData.put(GROUP_MINUTES_BONUS, timeBonus.minutesBonus());

        testDB.storeRentGroupData(rentGroupId.id(), rentGroupData);

        //  return new RentGroup(rentGroupId, rentGroupSessions, rentGroupMinutesBonus);
    }

    public RentPrice returnScooterFromGroup(ClientId clientId, RentGroupId rentGroupId, ScooterId scooterId) {
        var rentGroupSessions = testDB.getRentGroupSessions(rentGroupId.id());
        var rentGroupMinutesBonus = testDB.getRentGroupMinutesBonus(rentGroupId.id());
        var closedSession = sessionService.closeSession(clientId, scooterId);

        var rentPrice = new RentPrice(0d);

        //wylicz prosta cene a potem zmieniaj wartosc

        //wylicz podstawe
        //bonus serwis
        //
        if (rentGroupSessions.size() == 1) { ///odejmujemy bonus od ostatniego wypożyczenia
            rentPrice = priceCalculator.calculate(closedSession, rentGroupMinutesBonus);
        } else {
            rentPrice = priceCalculator.calculate(closedSession);
        }

        rentGroupSessions.removeIf(session -> session.scooterId().id().equals(scooterId.id()));
        HashMap<String, Object> rentGroupData = new HashMap<>();
        rentGroupData.put(GROUP_SESSIONS, rentGroupSessions);
        rentGroupData.put(GROUP_MINUTES_BONUS, rentGroupMinutesBonus.minutesBonus());
        testDB.storeRentGroupData(rentGroupId.id(), rentGroupData);

        return rentPrice;
    }

    //probably not needed
    public Session rentScooter(ClientId clientId, ScooterId scooterId) {

        var activeSessionsNumber = sessionService.getActiveClientSessionsNumber(clientId);
        var priceFactor = factorService.calculateFactor(activeSessionsNumber);

        return sessionService.createSession(clientId, scooterId, priceFactor);
    }

    //temporary it returns price.
    public RentPrice returnScooter(ClientId clientId, ScooterId scooterId) {
        var closedSession = sessionService.closeSession(clientId, scooterId);
        ///charger service should take this value
        return priceCalculator.calculate(closedSession);
    }

}


