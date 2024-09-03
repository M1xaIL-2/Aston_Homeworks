package com.company;

public class TwelfthTask {
    public static void main(String[] args) {
        lowerSix2Double ();
}

 public static void lowerSix2Double() {
     int [ ] myarr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
     for (int i = 0; i < myarr.length; i++) {
         if (myarr[i] < 6) {myarr[i] = myarr[i] * 2;
                           }
         System.out.print(myarr[i] + " ");
     }

 }
}
