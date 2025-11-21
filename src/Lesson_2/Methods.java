package Lesson_2;

import java.util.Scanner;

public class Methods {
    public static void printThreeWords() {
//  Выводит в столбец три слова: Orange, Banana, Apple

        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
/*
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
}
