package company.rental;

import company.ClientId;
import company.rental.session.Session;
import company.repository.TestDB;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PriceCalculatorTest {

    TestDB testDB = new TestDB();

    ClientId clientId = new ClientId(1L);
    ScooterId scooterId1 = new ScooterId(101L);

    @Test
    void shouldReturn1ForNoActiveSession() {
        PriceCalculator priceCalculator = new PriceCalculator(testDB);

        LocalDateTime rentFor10Minutes = LocalDateTime.now().plusMinutes(10).plusSeconds(1);
        double defaultPriceFactor = 1.0;

        Session session = new Session(scooterId1, LocalDateTime.now(), rentFor10Minutes, clientId, defaultPriceFactor);

        assertEquals(new BigDecimal("100.00"), priceCalculator.calculate(session).getPrice());
    }
}