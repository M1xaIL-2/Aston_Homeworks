package com.company;

public class TenthTask {
    public static void main(String[] args) {
        replaceZero();

    }

 public static void replaceZero() {
     int [ ] myArr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
     for (int i = 0; i < myArr.length; i++) {
         if (myArr[i] == 0) {myArr[i] = 1;
                            }
         else {myArr[i] = 0;
              }
         System.out.print(myArr[i] + ",");
     }

 }

}
