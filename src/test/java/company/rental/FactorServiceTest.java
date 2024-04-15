package company.rental;

import company.repository.TestDB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FactorServiceTest {

    TestDB testDB = new TestDB();

    @Test
    void shouldReturn1ForNoActiveSession() {
        FactorService factorService = new FactorService(testDB);

        assertEquals(1.0, factorService.calculateFactor(0).priceFactor());
    }

    @ParameterizedTest
    @CsvSource({"3,0.95", "4,0.94", "5,0.915", "6,0.90"})
    void shouldTestFactorCalculating(String numberOfSessions, String expectedPriceFactor) {
        FactorService factorService = new FactorService(testDB);

        assertEquals(Double.valueOf(expectedPriceFactor), factorService.calculateFactor(Integer.parseInt(numberOfSessions)).priceFactor());
    }
}