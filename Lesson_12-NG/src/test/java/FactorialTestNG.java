import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTestNG {
    @Test
    public void groupAssertions() {
        assertEquals(1, FactorialNG.number(0));
        assertEquals(2, FactorialNG.number(2));
        assertEquals(6, FactorialNG.number(3));
        assertEquals(120, FactorialNG.number(5));
        assertEquals(720, FactorialNG.number(6));
    }
}
