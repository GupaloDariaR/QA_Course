package test.java.Lesson_7_junit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static main.java.Lesson_7_junit_5.AreaOfTriangle.getAreaOfTriangle;
import static org.junit.jupiter.api.Assertions.*;

public class AreaOfTriangleTest {

    @DisplayName("Площадь треугольника")
    @Test
    public void getAreaOfTriangleTest() {
        assertEquals(15, getAreaOfTriangle(5, 6));
    }

    @DisplayName("Выброс исключения IllegalArgumentException " +
            "при неположительном значении стороны и/или высоты")
    @ParameterizedTest
    @CsvSource({"0, 6", "5, -1", "0, 0", "-5, -6"})
    public void getIllegalArgumentExceptionInAreaOfTriangleTest(int side, int heightToSide) {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> getAreaOfTriangle(side, heightToSide));
        assertTrue(e.getMessage().contains("Сторона и высота должны " +
                "принимать целые положительные значения"));
    }
}
