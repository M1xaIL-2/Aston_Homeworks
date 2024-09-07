package com.company;

import java.util.Scanner;

public class NinthTask {
    public static void main(String[] args) {
        System.out.println(leapYear());
    }

    public static boolean leapYear() {
        System.out.println("Введите год для проверки и нажмите Enter");
        Scanner year = new Scanner(System.in);
        int a = year.nextInt();
        return (a % 4 == 0 & a % 100 != 0) || (a % 400 == 0);
    }
}