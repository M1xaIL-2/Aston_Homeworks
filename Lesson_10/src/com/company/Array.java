package com.company;

public class Array {
       //метод "пробрасывания" исключения MyArraySizeException и MyArrayDataException
    public static int elementsSum(String[][] Arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (Arr.length != 4){
            throw new MyArraySizeException("Количество строк должно быть 4");
        }
        for (String [] stolb : Arr) {
            if (stolb.length!=4) {
                throw new MyArraySizeException("Количество столбцов должно быть 4");
            }
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






