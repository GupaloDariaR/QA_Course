package test.java.Lesson_7_testng;

import org.testng.annotations.Test;

import static main.java.Lesson_7_testng.Comparing.compareTwoIntegers;
import static org.testng.Assert.*;

public class ComparingTest {

    @Test(testName = "Сравнение двух чисел")
    public void compareTwoIntegersTest() {
          assertEquals(compareTwoIntegers(1, 2), "1 меньше 2");
          assertEquals(compareTwoIntegers(2, -1), "2 больше -1");
          assertEquals(compareTwoIntegers(2, 2), "2 равно 2");
    }
}
