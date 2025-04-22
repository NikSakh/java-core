package ru.mentee.power.variables;

public class PersonalCard {
    public static void main(String[] args) {
        String name = "Nikita";
        String surname = "Sakharov";
        int age = 25;
        String city = "Moscow";
        int height = 180;
        double weight = 75.5;
        boolean ifStudent = true;
        char nameFirstLetter = 'N';
        System.out.println("===== PERSONAL CARD =====");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age + " years old");
        System.out.println("City: " + city);
        System.out.println("Height: " + height + " cm");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Student: " + ifStudent);
        System.out.println("First letter of the name: " + nameFirstLetter);
        System.out.println("==========================");
    }
}