package com.company;

import java.util.Scanner;

public class EighthTask {
    public static void main(String[] args) {
        System.out.println("Введите необходимую строку и нажмите Enter");
        Scanner scan = new Scanner(System.in);
        String a = scan.nextLine();
        System.out.println("Введите необходимое число и нажмите Enter");
        int x = scan.nextInt();
        string2Print(a, x);
    }

    public static void string2Print(String a, int x) {
        for (int i = 0; i < x; i++) {
            System.out.println(a);
        }
    }
}
