package com.company;

import java.util.Scanner;

public class FourteenthTask {
    public static void main(String[] args) {
        lenValue();

}

 public static void lenValue() {
     Scanner chislo = new Scanner(System.in);
     int len = chislo.nextInt();
     int initialValue = chislo.nextInt();
     int [ ] array = new int [len];

     for (int i = 0; i < array.length; i++)               {
                   array [i] = initialValue;
     System.out.println("array[" + i + "] = " + array[i]);}

 }
}
