package com.company;
import java.util.Scanner;

public class SecondTask {
    public static void main (String[] args) {
        checkSumSign();
    }

 public static void checkSumSign() {
        Scanner number = new Scanner (System.in);
        int a =number.nextInt();
        int b =number.nextInt();

        if ( a+b >= 0) {System.out.println("Сумма положительная");
                       }
        else {System.out.println("Сумма отрицательная");
             }
 }
}
