package com.company;

import java.util.Scanner;

public class FourteenthTask {
    public static void main(String[] args) {
        Scanner chislo = new Scanner(System.in);
        int len = chislo.nextInt();
        int initialValue = chislo.nextInt();
        lenValue(len, initialValue);
    }

    public static void lenValue(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
            System.out.println("array[" + i + "] = " + array[i]);
        }
    }
}
