package main.java.Lesson_7_junit_5;

public class AreaOfTriangle {

    public static double getAreaOfTriangle(int side, int heightToSide) throws IllegalArgumentException{

        if (side <= 0 || heightToSide <= 0) {
            throw new IllegalArgumentException("Сторона и высота должны принимать целые положительные значения");
        }

        return 0.5 * side * heightToSide;
    }
}
