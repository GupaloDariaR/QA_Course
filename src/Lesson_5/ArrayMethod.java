package Lesson_5;

public class ArrayMethod {

    public static int getSumOfArrayCells (String[][] array) {
//      Проверка, что размер массива соответствует допустимому
        checkingArraySize(4, 4, array);

        int sumOfArrayCells = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int cellValue = Integer.parseInt(array[i][j]);
                    sumOfArrayCells += cellValue;
                } catch (MyArrayDataException e) {
                    System.out.printf("Неудалось преобразовать элемент [%d][%d] массива в число. " +
                            "Массив должен содержать только целые числа.", i, j);
                }
            }
        }

        return sumOfArrayCells;
    }

    public static void checkingArraySize(int sizeOfExternalArray, int sizeOfInternalArray, String[][] array) {
        if (array.length != sizeOfExternalArray) {
            try {
                throw new MyArraySizeException();
            } catch (MyArraySizeException e) {
                System.out.println("В метод getSumOfArrayCells передан массив неверного размера: " +
                        "внешний массив должен иметь размер 4");
            }
        }

        for (String[] internalArray : array) {
            if (internalArray.length != sizeOfInternalArray) {
                try {
                    throw new MyArraySizeException();
                } catch (MyArraySizeException e) {
                    System.out.println("В метод getSumOfArrayCells передан массив неверного размера: " +
                            "внутренние массивы должны иметь размер 4");
                }
            }
        }
    }

    public static class MyArraySizeException extends Exception { }

    public static class MyArrayDataException extends NumberFormatException { }
}
