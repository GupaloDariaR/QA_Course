package test.java.Lesson_7_testng;

import org.testng.annotations.Test;

import static main.java.Lesson_7_testng.FactorialOfNumber.getFactorialOfNumber;
import static org.testng.Assert.*;

public class FactorialOfNumberTest {

    @Test(testName = "Факториал числа")
    public void getFactorialOfNumberTest() {
        assertEquals(getFactorialOfNumber(0), 1);
        assertEquals(getFactorialOfNumber(5), 120);
    }

    @Test(
            testName = "Факториал отрицательного числа",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Факториал отрицателного числа не определен"
    )
    public void illegalArgumentExceptionInGetFactorialOfNumber() {
        getFactorialOfNumber(-1);
    }
}
