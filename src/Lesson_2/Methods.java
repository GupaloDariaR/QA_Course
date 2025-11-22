package Lesson_2;

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
    Метод должен вернуть true, если число отрицательное,
    и вернуть false если положительное.
    Замечание: ноль считаем положительным числом.
 */
        return number < 0;
    }
}
