package company.maintanace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    @Test
    void shouldThrowExceptionWhenCordsNotFromPoland() {
        //given
        Longitude longitude = new Longitude(2F); //Cords not from poland
        Latitude latitude = new Latitude(3F);

        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new Position(latitude, longitude));
        //check error message:
        assertEquals("Coordinates not from Poland", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenCordsNotFromPolandWhenLatIsOk() {
        //given
        Longitude longitude = new Longitude(2F); //Cords not from poland
        Latitude latitude = new Latitude(50F);

        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new Position(latitude, longitude));
        //check error message:
        assertEquals("Coordinates not from Poland", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenCordsNotFromPolandWhenLonIsOk() {
        //given
        Longitude longitude = new Longitude(15F); //Cords not from poland
        Latitude latitude = new Latitude(1F);

        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new Position(latitude, longitude));
        //check error message:
        assertEquals("Coordinates not from Poland", exception.getMessage());

    }

    //OFC below test can be excluded to separated clases
    @Test
    void shouldThrowExceptionWhenLatitiudeIsNull() {
        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new Latitude(null));
        //check error message:
        assertEquals("latitude cant be null", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenLongitudeIsNull() {
        //expected
        var exception = assertThrows(RuntimeException.class,
                () -> new Longitude(null));
        //check error message:
        assertEquals("longitude cant be null", exception.getMessage());

    }
}