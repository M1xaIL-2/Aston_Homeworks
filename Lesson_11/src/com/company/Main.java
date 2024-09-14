package com.company;

public class Main {

    public static void main(String[] args) {
        Array.main(args);

        System.out.println();

        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Иванов", "+79277549853");
        phoneBook.add("Иванов", "+79277549854");
        phoneBook.add("Чуйкова", "+79277549853");
        phoneBook.add("Сидорова", "+79376669955");
        phoneBook.add("Эйзенштейн", "+799999999");
        phoneBook.add("Сидорова", "89376669955");

        phoneBook.get("Иванов");
        phoneBook.get("Эйзенштейн");
        phoneBook.get("Сидорова");
        phoneBook.get("Чуйкова");

    }
}
