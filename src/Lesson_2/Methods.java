package Lesson_2;

import java.util.Arrays;
import java.util.Scanner;

public class Methods {
    public static void printThreeWords() {
/*
    Задание 1
    Выводит в столбец три слова: Orange, Banana, Apple
 */

        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
/*
    Задание 2
    Метод суммирует 2 переменные,
    если их сумма больше или равна 0,
    то выводит в консоль сообщение “Сумма положительная”,
    в противном случае - “Сумма отрицательная”;
*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число: ");
        int a = scanner.nextInt();
        System.out.println("Введите целое число: ");
        int b = scanner.nextInt();

        if (a + b >= 0){
            System.out.println("Сумма положительная");
        } else{
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
/*
    Задание 3
    Если value меньше 0 (0 включительно),
    то в консоль метод должен вывести сообщение “Красный”,
    если лежит в пределах от 0 (0 исключительно)
    до 100 (100 включительно), то “Желтый”,
    если больше 100 (100 исключительно) - “Зеленый”
*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число: ");
        int value = scanner.nextInt();

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
/*
    Задание 4
    Если a больше или равно b,
    то в консоль выводится сообщение “a >= b”,
    в противном случае “a < b”
 */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число: ");
        int a = scanner.nextInt();
        System.out.println("Введите целое число: ");
        int b = scanner.nextInt();

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean isSumInRange (int a, int b) {
/*
    Задание 5
    метод проверяет, что сумма чисел лежит
    в пределах от 10 до 20 (включительно),
    если да – вернуть true, в противном случае – false.
*/
        int summValue = a + b;
        return summValue >= 10 && summValue <= 20;
    }

    public static void printNumberSign(int number) {
/*
    Задание 6
    метод выводит в консоль, положительное ли число передали
    или отрицательное. Замечание: ноль считаем положительным числом.
 */
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean numberSign(int number) {
/*
    Задание 7
    Метод возвращает true, если число отрицательное,
    и false если положительное.
    Замечание: ноль считаем положительным числом.
 */
        return number < 0;
    }

    public static void printString(String str, int count) {
/*
    Задание 8
    Метод выводит в консоль указанную строку,
    указанное количество раз
 */
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
/*
    Задание 9
    Метод определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го,
    при этом каждый 400-й – високосный.
 */
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static int[] inversion(int[] array) {
/*
    Задание 10
    Метод применяет инверсию к массиву, состоящему из элементов 0 и 1.
 */
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                array[i] = 0;
            } else {
                array[i] = 1;
            }
        }
        return array;
    }

    public static void fillArray() {
/*
    Задание 11
    Метод с помощью цикла заполняет массив длиной 100
    значениями 1 2 3 4 5 6 7 8 ... 100;
 */
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i +1;
        }
        System.out.println(Arrays.toString(array));
    }
}
