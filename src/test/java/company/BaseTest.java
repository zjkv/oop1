package company;

import company.repository.TestDB;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected TestDB testDB = new TestDB();

    @BeforeEach
    public void setUp() {
        testDB = testDB.setUp();
    }



    // sesje przejazdow w ujeciu miesiecznym

    //
}
