package Lesson_2;

import java.util.Arrays;

import static Lesson_2.Methods.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        printThreeWords();

        System.out.println("\nЗадание 2");
        checkSumSign();

        System.out.println("\nЗадание 3");
        printColor();

        System.out.println("\nЗадание 4");
        compareNumbers();

        System.out.println("\nЗадание 5");
        System.out.println(isSumInRange(5, 5));
        System.out.println(isSumInRange(15, 5));
        System.out.println(isSumInRange(-15, 5));

        System.out.println("\nЗадание 6");
        printNumberSign(-1);
        printNumberSign(0);

        System.out.println("\nЗадание 7");
        System.out.println(numberSign(-1));
        System.out.println(numberSign(0));

        System.out.println("\nЗадание 8");
        printString("hello world", 3);

        System.out.println("\nЗадание 9");
        System.out.println(isLeapYear(2024));
        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(1900));

        System.out.println("\nЗадание 10");
        System.out.println(Arrays.toString(inversion(new int[] {1, 1, 1, 1, 1, 0, 0, 0, 0, 0})));

        System.out.println("\nЗадание 11");
        fillArray();

        System.out.println("\nЗадание 12");
        twiceArrayValues();

        System.out.println("\nЗадание 13");
        fillDiagonals(5);

        System.out.println("\nЗадание 14");
        System.out.println(Arrays.toString(createArray(3,3)));
    }
}
