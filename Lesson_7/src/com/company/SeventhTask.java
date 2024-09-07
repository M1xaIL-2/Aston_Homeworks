package com.company;

import java.util.Scanner;

public class SeventhTask {

    public static boolean pOrN(int a) {
        return (a < 0);
    }

    public static void main(String[] args) {
        System.out.println("Введите число для проверки и нажмите Enter");
        Scanner number = new Scanner(System.in);
        int a = number.nextInt();
        System.out.println(pOrN(a));
    }
}
