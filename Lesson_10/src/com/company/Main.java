package com.company;

import java.util.Scanner;

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

/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите кол-во строк");
        int x = scanner.nextInt();
        System.out.println("Введите кол-во столбцов");
        int y = scanner.nextInt();

        String[][] Arr5 = new String[x][y];
        for (int i = 0; i < Arr5.length; i++) {
            for (int j = 0; j < Arr5[i].length; j++) {
                System.out.println("Введите данные");
                Arr5[i][j] = scanner.nextLine();
            }
        }*/

        try {
            System.out.println("Сумма элементов массива Arr: " + Array.elementsSum(Arr));
        } catch (Array.MyArraySizeException | Array.MyArrayDataException e) /* обработка нескольких исключений сразу*/ {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr2: " + Array.elementsSum(Arr2));
        } catch (Array.MyArraySizeException | Array.MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr3: " + Array.elementsSum(Arr3));
        } catch (Array.MyArraySizeException | Array.MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива Arr4: " + Array.elementsSum(Arr4));
        } catch (Array.MyArraySizeException | Array.MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
/*
        try {
            System.out.println("Сумма элементов массива Arr5: " + Array.elementsSum(Arr5));
        } catch (Array.MyArraySizeException | Array.MyArrayDataException e) {
            System.out.println(e.getMessage());
        }*/
    }
}

