package com.company;

import java.util.Scanner;

public class SixthTask {
    public static void main (String[] args) {
        posOrNeg();
}

 public static void posOrNeg() {
        Scanner number = new Scanner (System.in);
        int a = number.nextInt();

        if (a >= 0) {System.out.println("Positive");
                    }
        else {System.out.println("Negative");
             }
  }
}
