import org.junit.jupiter.api.BeforeEach;
import repository.TestDB;

public abstract class BaseTest {

    TestDB testDB;

    @BeforeEach
    public void setUp() {
        testDB = new TestDB();
    }

}
