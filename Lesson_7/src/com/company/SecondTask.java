package com.company;

import java.util.Scanner;

public class SecondTask {
    public static void main(String[] args) {
        checkSumSign();
    }

    public static void checkSumSign() {
        System.out.println("Введите число №1");
        Scanner number = new Scanner(System.in);
        int a = number.nextInt();
        System.out.println("Введите число №2");
        int b = number.nextInt();

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
}
