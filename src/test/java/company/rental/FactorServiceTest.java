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

    //podajemy liczbe sessji które aktualnie są otwarte. 2 otwarte sesje czyli otwieramy 3 (Wyliczamy price factor dla 3)
    @ParameterizedTest
    @CsvSource({"2,0.95", "3,0.94", "4,0.915", "5,0.90"})
    void shouldTestFactorCalculating(String currentActiveSession, String expectedPriceFactor) {
        FactorService factorService = new FactorService(testDB);

        assertEquals(Double.valueOf(expectedPriceFactor), factorService.calculateFactor(Integer.parseInt(currentActiveSession)).priceFactor());
    }
}