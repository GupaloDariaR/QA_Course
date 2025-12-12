package main.java.Lesson_7_junit_5;

public class Comparing {

    public static String compareTwoIntegers(int a, int b) {
        if (a < b)
            return String.format("%d меньше %d", a, b);

        else if (a > b)
            return String.format("%d больше %d", a, b);

        else
            return String.format("%d равно %d", a, b);
    }
}
