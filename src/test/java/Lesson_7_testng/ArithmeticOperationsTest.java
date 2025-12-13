package test.java.Lesson_7_testng;

import org.testng.annotations.Test;

import static main.java.Lesson_7_testng.ArithmeticOperations.*;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test(testName = "Сложение")
    public void additionTest() {
        assertEquals(addition(4, 6), 10);
        assertEquals(addition(-4, -6), -10);
        assertEquals(addition(4, -6), -2);
        assertEquals(addition(-4, 6), 2);
    }

    @Test(testName = "Вычитание")
    public void subtractionTest() {
        assertEquals(subtraction(10, 3), 7);
        assertEquals(subtraction(3, 10), -7);
        assertEquals(subtraction(-3, 10), -13);
        assertEquals(subtraction(3, -10), 13);
        assertEquals(subtraction(-3, -10), 7);
    }

    @Test(testName = "Деление")
    public void divisionTest() {
        assertEquals(division(10, 2), 5f);
        assertEquals(division(2, 10), 0.2f);
        assertEquals(division(-10, 5), -2f);
        assertEquals(division(5, -10), -0.5f);
        assertEquals(division(-5, -10), 0.5f);
        assertEquals(division(0, -10), -0f);
        assertEquals(division(0, 10), 0f);
    }

    @Test(
            testName = "Деление на ноль",
            expectedExceptions = ArithmeticException.class
    )
    public void arithmeticExceptionInDivision() {
        division(1,0);
    }

    @Test(testName = "Умножение")
    public void multiplicationTest() {
        assertEquals(multiplication(2, 5), 10);
        assertEquals(multiplication(-2, -5), 10);
        assertEquals(multiplication(-2, 5), -10);
        assertEquals(multiplication(2, -5), -10);
        assertEquals(multiplication(2, 0), 0);
        assertEquals(multiplication(0, -5), 0);

    }
}
