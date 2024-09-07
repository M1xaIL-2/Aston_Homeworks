package com.company;

import java.util.Scanner;

public class FourthTask {
    public static void main(String[] args) {
        compareNumbers();
    }

    public static void compareNumbers() {
        System.out.println("Введите число a");
        Scanner value = new Scanner(System.in);
        int a = value.nextInt();
        System.out.println("Введите число b");
        int b = value.nextInt();

        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<b");
        }
    }
}
