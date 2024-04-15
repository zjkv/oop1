package company.rental.session;

import company.ClientId;
import company.rental.PriceFactor;
import company.rental.ScooterId;
import company.repository.TestDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static company.repository.TestDB.ACTIVE_SESSIONS;
import static company.repository.TestDB.CLIENT_LIST;

public class SessionService {

    private final TestDB testDB;

    public SessionService(TestDB testDB) {
        this.testDB = testDB;
    }

    //transactional
    public void createSession(ClientId clientId, ScooterId scooterId, PriceFactor priceFactor) {

        HashMap<String, Object> clientData = testDB.getClientData(clientId.id());
        var newSession = Session.createSession(scooterId, clientId, priceFactor.priceFactor());

        List<Session> activeSessions = new ArrayList<>();

        activeSessions.addAll((List<Session>) clientData.get(ACTIVE_SESSIONS));
        activeSessions.add(newSession);

        clientData.put(ACTIVE_SESSIONS, activeSessions);
        testDB.storeClientData(clientId.id(), clientData);


        //for scooter history
        HashMap<String, Object> scooterData = testDB.getScooterData(scooterId.id());

        List<ClientId> clientIdList = new ArrayList<>();
        clientIdList.addAll((List<ClientId>) scooterData.getOrDefault(CLIENT_LIST, new ArrayList<>()));
        clientIdList.add(clientId);

        scooterData.put(CLIENT_LIST, clientIdList);

        testDB.storeScooterData(scooterId.id(), scooterData);

    }

    public int getActiveClientSessionsNumber(ClientId clientId) {
        return testDB.getClientActiveSession(clientId.id()).size();
    }

}


