package company.rental;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsageTimeTest {

    @Test
    void shouldThrowExceptionWhenMinutesAreNull() {
        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new UsageTime(null));
        //check error message:
        assertEquals("Usage time cant be null", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenUsageTimeIsLessThan0() {
        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new UsageTime(-5));
        //check error message:
        assertEquals("Usage time cannot be les than 0", exception.getMessage());

    }
}