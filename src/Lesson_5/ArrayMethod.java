package Lesson_5;

public class ArrayMethod {

    public static int getSumOfArrayCells (String[][] array) throws MyArraySizeException, MyArrayDataException {
//      Проверка, что размер массива соответствует допустимому
        checkingArraySize(4, 4, array);

        int sumOfArrayCells = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int cellValue = Integer.parseInt(array[i][j]);
                    sumOfArrayCells += cellValue;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sumOfArrayCells;
    }

    public static void checkingArraySize(int sizeOfExternalArray, int sizeOfInternalArray, String[][] array) throws MyArraySizeException {
        if (array.length != sizeOfExternalArray) {
            throw new MyArraySizeException("В метод getSumOfArrayCells передан массив неверного размера: " +
                    "внешний массив должен иметь размер 4");
        }

        for (String[] internalArray : array) {
            if (internalArray.length != sizeOfInternalArray) {
                throw new MyArraySizeException("В метод getSumOfArrayCells передан массив неверного размера: " +
                        "внутренние массивы должны иметь размер 4");
            }
        }
    }

    public static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static class MyArrayDataException extends Exception {
        public MyArrayDataException (int row, int col) {
            super(String.format("Неудалось преобразовать элемент [%d][%d] массива в число. " +
                    "Массив должен содержать только целые числа.", row, col));
        }
    }
}
