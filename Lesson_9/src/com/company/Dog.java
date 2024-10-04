package com.company;

public class Dog extends Animal {
    private String dogName;
    private static int dogCount = 0;
    private final int maxRunDistance = 500;
    private final int maxDistanceToSwim = 10;

    public Dog(String dogName) {
        super(500,10);
        this.dogName = dogName;
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println("Длина препятствия не может быть отрицательной");
        }
        if (distance >= 0 & distance <= maxRunDistance) {
            System.out.println(dogName + " пробежал " + distance + " м.");
        } else {
            System.out.println("Собака такую длину препятствия не пробежит.");
        }
    }

    @Override
    public void swim(int swim) {
        if (swim < 0) {
            System.out.println("Длина препятствия не может быть отрицательной");
        } else if (swim >= 0 & swim <= maxDistanceToSwim) {
            System.out.println(dogName + " проплавал " + swim + " м.");
        } else {
            System.out.println("Собака такую длину препятствия не проплывёт.");
        }
    }
}
