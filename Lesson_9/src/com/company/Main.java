package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner chislo = new Scanner(System.in);
        Cat[] catAnimal = new Cat[3];
        catAnimal[0] = new Cat("Мурзик");
        catAnimal[1] = new Cat("Кузя");
        catAnimal[2] = new Cat("Муся");

        Dog bobik = new Dog("Бобик");
        Dog barbos = new Dog("Барбос");
        bobik.run(150);
        bobik.swim(5);
        barbos.run(450);
        barbos.swim(9);

        for (int i = 0; i < catAnimal.length; i++) {
            System.out.println("Введите длину препятсвия для бега");
            int dist = chislo.nextInt();
            catAnimal[i].run(dist);
            System.out.println("Введите длину препятсвия для плавания");
            int sail = chislo.nextInt();
            catAnimal[i].swim(sail);
        }

        System.out.println("Всего животных:" + Animal.getAnimalCount());
        System.out.println("Всего кошек:" + Cat.getCatCount());
        System.out.println("Общее количество собак:" + Dog.getDogCount());

        CatBowl bowl = new CatBowl(20);

        for (int i = 0; i < catAnimal.length; i++) {
            catAnimal[i].eat(bowl);
            System.out.println(catAnimal[i].getName() + " сытый: " + catAnimal[i].satiety());
            System.out.println("Еды осталось в миске: " + bowl.getFoodAmount());
            System.out.println();
        }

        bowl.addFood(50);
        System.out.println();

       forma(); // вызывается метод forma
    }

    public static void forma () {
        Figure circle = new Circle(5, "Red", "Black");
        Figure triangle = new Triangle(3, 4, 5, "Green", "Blue");
        Figure rectangle = new Rectangle(4, 6, "Yellow", "Purple");

        printFigureDetails(circle);
        printFigureDetails(triangle);
        printFigureDetails(rectangle);
    }

        private static void printFigureDetails(Figure shape) {
            System.out.println("Фигура: " + shape.getClass().getSimpleName());
            System.out.println("Цвет заливки: " + shape.getFillColor());
            System.out.println("Цвет границы: " + shape.getBorderColor());
            System.out.println("Периметр: " + shape.calculatePerimeter());
            System.out.println("Площадь: " + shape.calculateArea());
            System.out.println();
        }
}

