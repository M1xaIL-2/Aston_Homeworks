package com.company;

import java.util.Scanner;

public class SixthTask {
    public static void main(String[] args) {
        Scanner number = new Scanner(System.in);
        int a = number.nextInt();
        posOrNeg(a);
    }

    public static void posOrNeg(int a) {
        if (a >= 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }
}
