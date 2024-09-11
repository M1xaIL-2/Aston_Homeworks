package com.company;

public class Array {
    static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message); //передача сообщения родительскому классу Exception
        }
    }

    static class MyArrayDataException extends Exception {
        public MyArrayDataException(String message) {
            super(message);
        }
    }

    //метод "пробрасывания" исключения MyArraySizeException и MyArrayDataException
    public static int elementsSum(String[][] Arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (Arr.length != 4 || Arr[0].length != 4) {
            throw new MyArraySizeException("Размер массива несовпадает с размеров 4х4");
        }

        for (int i = 0; i < Arr.length; i++) {
            for (int j = 0; j < Arr[i].length; j++) {
                try {
                    sum = sum + Integer.valueOf(Arr[i][j]); // для преобразования строки в число
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования данных в строке " + (i + 1) + " и столбце " + (j + 1) + ".");
                }
            }
        }
        return sum;
    }
}






