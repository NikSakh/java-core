package ru.mentee.power.conditions;

import java.util.Scanner;

public class TrafficLight {

    /**
     * Возвращает рекомендацию для пешехода в зависимости от сигнала светофора.
     *
     * @param signal строковое представление сигнала ("Красный", "Желтый", "Зеленый")
     * @return Рекомендация для пешехода
     */
    public static String getRecommendation(String signal) {
        if (signal == null) {
            return "Некорректный сигнал!";
        }

        switch (signal.toLowerCase()) {
            case "красный":
                return "Стой на месте!";
            case "желтый":
                return "Приготовься, но подожди!";
            case "зеленый":
                return "Можно переходить дорогу!";
            default:
                return "Некорректный сигнал!";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Симулятор светофора для пешеходов");
        System.out.println("----------------------------------");

        while (true) {
            System.out.print("\nВведите сигнал светофора (Красный/Желтый/Зеленый) или 'выход' для завершения: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("выход")) {
                break;
            }

            String recommendation = getRecommendation(input);
            System.out.println("Рекомендация: " + recommendation);

            // Визуализация светофора без эмодзи
            printTrafficLight(input);
        }

        System.out.println("\nПрограмма завершена. Будьте осторожны на дороге!");
        scanner.close();
    }

    private static void printTrafficLight(String signal) {
        System.out.println("\n[СВЕТОФОР]");
        System.out.println("-----");
        System.out.println("| " + (signal.equalsIgnoreCase("красный") ? "X" : "O") + " | Красный");
        System.out.println("| " + (signal.equalsIgnoreCase("желтый") ? "X" : "O") + " | Желтый");
        System.out.println("| " + (signal.equalsIgnoreCase("зеленый") ? "X" : "O") + " | Зеленый");
        System.out.println("-----");
    }
}