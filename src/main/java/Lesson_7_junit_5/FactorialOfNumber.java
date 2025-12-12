package main.java.Lesson_7_junit_5;

public class FactorialOfNumber {

    public static long getFactorialOfNumber(int number) {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
