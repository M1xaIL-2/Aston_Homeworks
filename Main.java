package com.company;

public class Main {

    public static void main(String[] args) {
        Person.personOfArray(args);

        System.out.println();

        Park park1 = new Park("Внешний");
        Park park2 = new Park("Загородный");
        Park.Attraction attraction1 = new Park("Внешний").new Attraction("Кривое зеркало", "9:00-18:00", 500);
        Park.Attraction attraction2 = new Park("Внешний").new Attraction("Качели", "10:00-18:00", 200);
        Park.Attraction attraction3 = new Park("Загородный").new Attraction("Тир", "9:00-20:00", 1500);
        park1.parkInfo();
        park2.parkInfo();
        attraction1.attractionInfo();
        attraction2.attractionInfo();
        attraction3.attractionInfo();
    }
}
