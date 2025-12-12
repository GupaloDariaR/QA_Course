package main.java.Lesson_7_testng;

public class FactorialOfNumber {

    public static long getFactorialOfNumber(int number) throws IllegalArgumentException{
        if (number < 0) {
            throw new IllegalArgumentException("Факториал отрицателного числа не определен");
        }

        if (number == 0) {
            return 1;
        }

        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
