package com.company;

public abstract class Animal {
    private int maxRunDistance;
    private int maxSwimDistance;
    private static int animalCount = 0;

    public Animal(int maxRunDistance, int maxSwimDistance) {
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        animalCount++;
    }

    public static int getAnimalCount(){
        return animalCount;
    }

    public abstract void run(int distance);
    public abstract void swim(int swim);
}