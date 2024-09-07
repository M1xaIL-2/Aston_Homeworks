package com.company;

import java.util.Scanner;

public class ThirteenthTask {
    public static void main(String[] args) {
        diagOnlyOne();
    }

    public static void diagOnlyOne() {
        System.out.println("Насколько квадратен будет массив?");
        Scanner numb = new Scanner(System.in);
        int a = numb.nextInt();
        int[][] sqr = new int[a][a];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if ((i == j) || ((j + i) == (a - 1))) {
                    sqr[i][j] = 1;
                } else {
                    sqr[i][j] = numb.nextInt();
                }
            }
        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print(sqr[i][j] + " ");
            }
            System.out.println();
        }
    }
}