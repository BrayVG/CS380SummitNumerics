import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.Before;

public class AdminRecordTest {
    private AdminRecord adminRecord;


    @Before
    public void setUp() {
        adminRecord = new AdminRecord("Diva", 1, 100); // test username | rank | finalScore
    }


    @Test
    public void getUsernameReturnsCorrectUsername() {
        AdminRecord record = new AdminRecord("Diva", 1, 100);

        assertEquals("Diva", record.getUsername());
    }

    @Test
    public void getRankReturnsCorrectRank() {
        AdminRecord record = new AdminRecord("Diva", 1, 100);

        assertEquals(1, record.getRank());
    }

    @Test
    public void getFinalScoreReturnsCorrectFinalScore() {
        AdminRecord record = new AdminRecord("Diva", 1, 100);

        assertEquals(100, record.getFinalScore());
    }

    @Test
    public void constructorStoresAllDataCorrectly() {
        AdminRecord record = new AdminRecord("Tom", 2, 80);

        assertEquals("Tom", record.getUsername());
        assertEquals(2, record.getRank());
        assertEquals(80, record.getFinalScore());
    }


}