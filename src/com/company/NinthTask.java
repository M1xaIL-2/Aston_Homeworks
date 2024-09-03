package com.company;

import java.util.Scanner;

public class NinthTask {
    public static void main(String[] args) {
        leapYear();
    }
 public static void leapYear() {
        Scanner year = new Scanner(System.in);
        System.out.println("Введите год для проверки и нажмите Enter");
        int a = year.nextInt();
        boolean x = ((a % 4 == 0 & a % 100 != 0) || (a % 400 == 0));

        if (x) {System.out.println(x);
               }
        else {System.out.println(x);
             }
 }
}