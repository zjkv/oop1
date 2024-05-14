package company.rental.rentGroup;

import company.rental.session.Session;

import java.util.List;

public record RentGroup(RentGroupId rentGroupId, List<Session> sessionList, TimeBonus timeBonus) {

}
