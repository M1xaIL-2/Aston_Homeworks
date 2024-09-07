package com.company;

public class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    public void parkInfo() {
        System.out.println("Название парка: " + parkName);
    }

    public class Attraction {
        private String name;
        private String openingHours;
        private int price;

        public Attraction(String name, String openingHours, int price) {
            this.name = name;
            this.openingHours = openingHours;
            this.price = price;
        }

        public void main(String[] args) {
            Attraction attraction1 = new Attraction("Кривое зеркало", "9:00-18:00", 500);
            Attraction attraction2 = new Attraction("Качели", "10:00-18:00", 200);
            Attraction attraction3 = new Attraction("Качели", "10:00-18:00", 200);
        }


        public void attractionInfo() {
            System.out.println("Название аттракциона: " + name + "; Время работы: " + openingHours + ";Стоимость: " + price);
        }
    }

    public static void main(String[] args) {
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
