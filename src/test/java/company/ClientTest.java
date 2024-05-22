package company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends BaseTest {


    @Test
    void shouldCheckIfSubscriptionAppliesDiscount() {
        // given
        ClientId clientId = new ClientId(1L);

        // when
        Client client = new Client(clientId);

    }
}