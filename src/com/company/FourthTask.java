package com.company;

import java.util.Scanner;

public class FourthTask {
    public static void main (String[] args) {
        compareNumbers();
}

    public static void compareNumbers() {
        Scanner value = new Scanner (System.in);
        int a = value.nextInt();
        int b = value.nextInt();

        if (a>=b) {System.out.println("a>=b");
                  }
        else {System.out.println("a<b");
             }
    }
}
