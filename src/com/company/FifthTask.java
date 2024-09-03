package com.company;

import java.util.Scanner;

public class FifthTask {
    public static void main (String[] args) {
        compareNumberSum();
}

 public static void compareNumberSum() {
        Scanner value = new Scanner (System.in);
        int a = value.nextInt();
        int b = value.nextInt();
        int x = a + b;
        boolean y = (x >= 10 & x <= 20);

        if (y) {System.out.println(y);
               }
        else {System.out.println(y);
             }
 }
}
