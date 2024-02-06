package enoch.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @DisplayName("JUnit Sanity Test - should fail")
    @Test
    public void shouldBeAbleToRunTest() {
        assertEquals(1,1);
    }
}
