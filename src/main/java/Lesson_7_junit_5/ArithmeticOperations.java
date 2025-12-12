package main.java.Lesson_7_junit_5;

public class ArithmeticOperations {

    public static int addition(int a, int b) {
        return a + b;
    }

    public static int subtraction(int a, int b) {
        return a - b;
    }

    public static float division(int a, int b) throws ArithmeticException{
        if (b == 0)
            throw new ArithmeticException("Деление на ноль");
        else
            return (float) a / b;
    }

    public static int multiplication(int a, int b) {
        return a * b;
    }
}
