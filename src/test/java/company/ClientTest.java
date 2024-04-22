package company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({"true,true", "false,false"})
    void shouldCheckIfHaveSubscription(boolean subscriptionParameter, boolean expectedSubscription) {
        // given
        ClientId clientId = new ClientId(1L);

        // when
        Client clientWithSubscription = new Client(clientId, subscriptionParameter);
        var actualSubscription = clientWithSubscription.isSubscriptionType();


        // then
        assertEquals(actualSubscription, expectedSubscription);
    }
}