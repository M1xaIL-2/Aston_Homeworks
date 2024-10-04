package com.company;

public class Cat extends Animal {
    private String catName;
    private static int catCount = 0;
    private final int maxRunDistance = 200;
    private final String catDistanceToSwim = "Кот не умеет плавать";
    private boolean satiety = false;

    public Cat (String catName) {
        super(200,0);
        this.catName = catName;
        catCount++;
    }

    public static int getCatCount () {
        return catCount;
    }

    public String getName () {
        return catName;
    }

    public void eat(CatBowl bowl) {
        int eat = 10; // Количество еды, которое кот хочет съесть
        if (bowl.feedCat(eat)) {
            satiety = true;
            System.out.println(catName + " покушал из миски.");
        } else {
            System.out.println(catName + " не хватило еды.");
        }
    }

    public boolean satiety() {
        return satiety;
    }


    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println("Длина препятствия не может быть отрицательной");
        } else if (distance >= 0 & distance <= maxRunDistance) {
            System.out.println(catName + " пробежал " + distance + " м.");
        } else {
            System.out.println("Кот такую длину препятствия не пробежит.");
        }
    }

    @Override
    public void swim(int swim) {
        if (swim < 0) {
            System.out.println("Длина препятствия не может быть отрицательной");
        } else {
            System.out.println(catDistanceToSwim);
        }
    }
}
