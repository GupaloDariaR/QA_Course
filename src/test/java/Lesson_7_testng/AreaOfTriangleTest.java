package test.java.Lesson_7_testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static main.java.Lesson_7_testng.AreaOfTriangle.getAreaOfTriangle;
import static org.testng.Assert.*;

public class AreaOfTriangleTest {

    @Test(testName = "Площадь треугольника")
    public void getAreaOfTriangleTest() {
        assertEquals(getAreaOfTriangle(5, 6), 15);
    }

    @Test(
            testName = "Выброс исключения IllegalArgumentException " +
            "при неположительном значении стороны и/или высоты",
            dataProvider = "areaOfTriangleData",
            expectedExceptions = IllegalArgumentException.class
    )
    public void illegalArgumentExceptionInGetAreaOfTriangleTest(int side, int heightToSide) {
        getAreaOfTriangle(side, heightToSide);
    }

    @DataProvider(name = "areaOfTriangleData")
    public Object[][] illegalArgumentExceptionInGetAreaOfTriangleData() {
        return new Object[][] {
                {0, 6},
                {5, -1},
                {0, 0},
                {-5, -6}
        };
    }
}
