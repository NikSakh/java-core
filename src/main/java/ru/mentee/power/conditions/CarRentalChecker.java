package ru.mentee.power.conditions;

import java.util.Scanner;

public class CarRentalChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос возраста
        System.out.print("Введите ваш возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        // Запрос наличия прав
        System.out.print("Есть ли у вас водительские права? (да/нет): ");
        String hasLicenseInput = scanner.nextLine().toLowerCase();
        boolean hasLicense = hasLicenseInput.equals("да");

        // Проверка условий аренды
        boolean canRent = age >= 18 && hasLicense;

        // Вывод результата
        if (canRent) {
            System.out.println("Вы можете арендовать автомобиль!");
        } else {
            System.out.println("Извините, вы не можете арендовать автомобиль.");

            if (age < 18) {
                System.out.println("Причина: вам меньше 18 лет.");
            }
            if (!hasLicense) {
                System.out.println("Причина: у вас нет водительских прав.");
            }
        }

        scanner.close();
    }
}