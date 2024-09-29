package com.company;

public class Main {
    public static void main(String[] args) {
        String[][] Arr = {
                {"10", "20", "30", "40"},
                {"50", "60", "70", "80"},
                {"90", "100", "110", "120"},
                {"130", "140", "150", "160"}
        };
        String[][] Arr2 = {
                {"10", "20", "30", "40"},
                {"50", "60", "70", "80"},
                {"90", "100", "110", "120"}
        };
        String[][] Arr3 = {
                {"10", "20", "30", "40"},
                {"50", "текст", "70", "80"},
                {"90", "100", "110", "120"},
                {"130", "140", "150", "5"}
        };
        String[][] Arr4 = {
                {"10", "20", "30"},
                {"50", "60", "70"},
                {"90", "100", "110"},
                {"130", "140", "150"}
        };

        String[][] Arr5 = {
                {"10", "20", "30", "40"},
                {"50", "60", "70", "80"},
                {"90", "100", "110", "120", "123", "123", "123", "123", "123"},
                {"130", "140", "150", "160"}
        };


        try {
            System.out.println("Сумма элементов массива Arr: " + Array.elementsSum(Arr));
        } catch (MyArraySizeException | MyArrayDataException e) /* обработка нескольких исключений сразу*/ {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr2: " + Array.elementsSum(Arr2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr3: " + Array.elementsSum(Arr3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr4: " + Array.elementsSum(Arr4));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr5: " + Array.elementsSum(Arr5));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}

