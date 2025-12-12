package test.java.Lesson_7_junit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static main.java.Lesson_7_junit_5.Comparing.compareTwoIntegers;
import static org.junit.jupiter.api.Assertions.*;

public class ComparingTest {

    @DisplayName("Сравнение двух чисел")
    @Test
    public void compareTwoIntegersTest() {
        assertAll(
                () -> assertEquals("1 меньше 2", compareTwoIntegers(1, 2)),
                () -> assertEquals("2 больше -1", compareTwoIntegers(2, -1)),
                () -> assertEquals("2 равно 2", compareTwoIntegers(2, 2))
        );
    }
}
