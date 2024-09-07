package com.company;

public class EleventhTask {
    public static void main(String[] args) {
        arrayHundred();
    }

    public static void arrayHundred() {
        int[] Arr = new int[100];
        int x = 1;
        for (int i = 0; i < 100; i++) {
            Arr[i] = x;
            x++;
            System.out.println("Arr[" + i + "] = " + Arr[i]);
        }
    }
}
