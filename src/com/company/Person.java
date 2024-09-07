package com.company;

public class Person {
    private String fullName;
    private String role;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String fullName, String role, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void personInfo() {
        System.out.println("ФИО: " + fullName + "; Должность: " + role + ";Почта: " + email
                + "; Телефон: " + phone + "; Зарплата: " + salary + "; Возраст: " + age);
    }

    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "89355479856", 30000, 30);
        persArray[1] = new Person("Semenov Igor", "Senior Engineer", "12345@mail.ru", "89270013663", 50000, 40);
        persArray[2] = new Person("Ignatiev Mikhail", "Director", "dir_mik@rambler.ru", "89202135476", 90000, 50);
        persArray[3] = new Person("Golovina Lyubov", "Secretary", "lyu35gol@gmail.com", "+79276245879", 35000, 35);
        persArray[4] = new Person("Pavlova Larisa", "Accountant", "ivivan@yandex.ru", "89256489785", 40000, 32);
        for (int i = 0; i < persArray.length; i++) {
            persArray[i].personInfo();
        }
    }
}
