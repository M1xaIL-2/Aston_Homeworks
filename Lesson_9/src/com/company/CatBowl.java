package com.company;

public class CatBowl {
    private int foodAmount;

    public CatBowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) { // Добавить еду в миску
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " еды в миску. Теперь в миске " + foodAmount + " еды.");
        } else {
            System.out.println("Количество добавляемой еды должно быть положительным.");
        }
    }

    public boolean feedCat(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
            return true; // Кот покушал
        } else {
            System.out.println("Недостаточно еды для кота");
            return false;
        }
    }
}
