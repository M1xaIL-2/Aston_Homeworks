package com.company;

import java.util.Scanner;

public class ThirdTask {
    public static void main(String[] args) {
        printColor();
    }

    public static void printColor() {
        System.out.println("Введите любое число");
        Scanner number = new Scanner(System.in);
        int value = number.nextInt();

        if (value <= 0) {
            System.out.println("Красный");
        } else {
            if (value <= 100) {
                System.out.println("Жёлтый");
            } else {
                System.out.println("Зелёный");
            }
        }
    }
}
