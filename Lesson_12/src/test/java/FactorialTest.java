import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FactorialTest {
    @DisplayName("Поиск факториал числа")
    @Test

    public void groupAssertions() {
        assertEquals(120, Factorial.number(5));
        assertEquals(2, Factorial.number(2));
        assertEquals(6, Factorial.number(3));
        assertEquals(720, Factorial.number(6));
        assertEquals(1, Factorial.number(0));
    }
}
