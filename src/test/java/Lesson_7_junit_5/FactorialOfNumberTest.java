package test.java.Lesson_7_junit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static main.java.Lesson_7_junit_5.FactorialOfNumber.getFactorialOfNumber;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialOfNumberTest {

    @DisplayName("Факториал числа")
    @Test
    public void getFactorialOfNumberTest() {
        assertAll(
                () -> assertEquals(1, getFactorialOfNumber(0)),
                () -> assertEquals(120, getFactorialOfNumber(5))
        );
    }

    @DisplayName("Факториал отрицательного числа")
    @Test
    public void illegalArgumentExceptionInGetFactorialOfNumber() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> getFactorialOfNumber(-1));
        assertTrue(e.getMessage().contains("Факториал отрицателного числа не определен"));
    }
}
