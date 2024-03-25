package company;

import company.repository.TestDB;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected TestDB testDB;

    @BeforeEach
    public void setUp() {
        testDB = new TestDB();
    }

}
