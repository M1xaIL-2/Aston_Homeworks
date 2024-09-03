package com.company;
import java.util.Scanner;

public class ThirdTask {
    public static void main (String[] args) {
        printColor();
}

    public static void printColor() {
        Scanner number = new Scanner (System.in);
        int value = number.nextInt();

        if (value <= 0) {System.out.println("Красный");
                        }
        if (value > 0 & value <= 100) {System.out.println("Жёлтый");
                                      }
       else {System.out.println("Зелёный");
            }
    }
}
