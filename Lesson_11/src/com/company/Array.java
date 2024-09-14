package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

import java.util.*;

public class Array {
    public static void main(String[] args) {

        String[] words = {
                "Маслёнка", "Пила", "Пакет", "Спички", "Ведро",
                "Дрова", "Шампур", "Вилка", "Топор", "Секатор",
                "Перчатки", "Лопата", "Радио", "Грабли", "Грабли",
                "Пакет", "Шампур", "Вилка", "Маслёнка"
        }; //19 итого

        ArrayList<String> things = new ArrayList<>(); //создаём Класс ArrayList (динамический массив), передав значения массива words

        for (String o : words) {
            things.add(o);
        }
        System.out.println("Массив состоит из следующих вещей: " + things);

        Set<String> noRepeats = new HashSet<>(things); //Вывести уникальные слова из динамического массива
        System.out.println("Список уникальных слов: " + noRepeats);

        Map<String, Integer> count = new HashMap<String, Integer>(); // подсчёт слов
        int b = 1;
        for (String a : things) {
            if (!count.containsKey(a)) //если не было подобного слово, добавляемс в коллекцию со значением 1
            {
                count.put(a, b);
            } else {
                b = count.get(a);
                count.put(a, ++b);
            }
        }
        System.out.println("Cлова и сколько раз встречаются " + count);

        // нашёл как вывести уникальные слова и их количество раз через Map
        for (Map.Entry<String, Integer> c : count.entrySet()) {
            System.out.println("Слово: " + c.getKey() + ", Количество раз/раза: " + c.getValue());
        }

    }
}
