package test.java.Lesson_7_junit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static main.java.Lesson_7_junit_5.ArithmeticOperations.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {

    @DisplayName("Сложение")
    @Test
    public void additionTest() {
        assertAll(
                () -> assertEquals(10, addition(4, 6)),
                () -> assertEquals(-10, addition(-4, -6)),
                () -> assertEquals(-2, addition(4, -6)),
                () -> assertEquals(2, addition(-4, 6))
        );
    }

    @DisplayName("Вычитание")
    @Test
    public void subtractionTest() {
        assertAll(
                () -> assertEquals(7, subtraction(10, 3)),
                () -> assertEquals(-7, subtraction(3, 10)),
                () -> assertEquals(-13, subtraction(-3, 10)),
                () -> assertEquals(13, subtraction(3, -10)),
                () -> assertEquals(7, subtraction(-3, -10))
        );
    }

    @DisplayName("Деление")
    @Test
    public void divisionTest() {
        assertAll(
                () -> assertEquals(5f, division(10, 2)),
                () -> assertEquals(0.2f, division(2, 10)),
                () -> assertEquals(-2f, division(-10, 5)),
                () -> assertEquals(-0.5f, division(5, -10)),
                () -> assertEquals(0.5f, division(-5, -10)),
                () -> assertEquals(-0f, division(0, -10)),
                () -> assertEquals(0f, division(0, 10))
        );
    }

    @DisplayName("Деление на ноль")
    @Test
    public void arithmeticExceptionInDivision() {
        ArithmeticException e = assertThrows(ArithmeticException.class,
                () -> division(1, 0));
        assertTrue(e.getMessage().contains("Деление на ноль"));
    }

    @DisplayName("Умножение")
    @Test
    public void multiplicationTest() {
        assertAll(
                () -> assertEquals(10, multiplication(2, 5)),
                () -> assertEquals(10, multiplication(-2, -5)),
                () -> assertEquals(-10, multiplication(-2, 5)),
                () -> assertEquals(-10, multiplication(2, -5)),
                () -> assertEquals(0, multiplication(2, 0)),
                () -> assertEquals(0, multiplication(0, -5))
        );
    }
}
