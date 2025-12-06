package Lesson_5;

import static Lesson_5.ArrayMethod.*;

public class App {
    public static void main(String[] args) throws ArrayMethod.MyArraySizeException, ArrayMethod.MyArrayDataException {
//      корректный вызов
        String[][] arr1 = new String[][] {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"}
        };
        System.out.println("Сумма ячеек массива: " + getSumOfArrayCells(arr1) + "\n");

//      проверка исключения MyArraySizeException
        try {
            System.out.println("Сумма ячеек массива: " + getSumOfArrayCells(new String[4][1]) + "\n");
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            System.out.println("Сумма ячеек массива: " + getSumOfArrayCells(new String[5][4]) + "\n");
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage() + "\n");
        }

//      проверка исключения MyArrayDataException
        try {
            String[][] arr2 = new String[][]{
                    {"1", "1", "1", "1"},
                    {"e", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"}
            };
            System.out.println("Сумма ячеек массива: " + getSumOfArrayCells(arr2) + "\n");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage() + "\n");
        }

//      код для генерации и поимки ArrayIndexOutOfBoundsException
        try {
            System.out.println("Значение ячейки [1][4] массива: " + arr1[1][4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
