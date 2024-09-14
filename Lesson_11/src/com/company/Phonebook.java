package com.company;

import java.util.HashSet;
import java.util.HashMap;

public class Phonebook {
    private HashMap<String, HashSet<String>> contacts;

    public Phonebook() {
        this.contacts = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (!contacts.containsKey(lastName)) {
            contacts.put(lastName, new HashSet<>());
        }
        contacts.get(lastName).add(phoneNumber);
    }

    public void get(String lastName) {
        HashSet<String> phoneNumbers = contacts.get(lastName);
        System.out.println(lastName + " " + phoneNumbers);
    }

    public static void main(String[] args) {
        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Иванов", "+79277549853");
        phoneBook.add("Иванов", "+79277549854");
        phoneBook.add("Чуйков", "+79277549853");
        phoneBook.add("Эйзенштейн", "+79677555555");
        phoneBook.add("Кутузов", "+79057549853");
        phoneBook.add("Перельман", "+794577777777");
        phoneBook.add("Эйзенштейн", "+799999999");

        phoneBook.get("Иванов");
        phoneBook.get("Эйзенштейн");
        phoneBook.get("Чуйков");
        phoneBook.get("Кутузов");

    }
}
