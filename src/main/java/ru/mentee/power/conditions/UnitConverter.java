package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UnitConverter {

    private static final double ERROR_CODE = -1.0;

    private static final List<String> LENGTH_UNITS = Arrays.asList("Метр", "Сантиметр", "Дюйм", "Фут");
    private static final List<String> WEIGHT_UNITS = Arrays.asList("Килограмм", "Грамм", "Фунт", "Унция");
    private static final List<String> TEMP_UNITS = Arrays.asList("Цельсий", "Фаренгейт", "Кельвин");

    /**
     * Конвертирует значение из одной единицы измерения в другую
     *
     * @param value значение для конвертации
     * @param fromUnit исходная единица измерения
     * @param toUnit целевая единица измерения
     * @return конвертированное значение или ERROR_CODE в случае ошибки
     */
    public double convert(double value, String fromUnit, String toUnit) {
        // Проверка на одинаковые единицы
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        // Проверка поддерживаются ли обе единицы
        if (!isUnitSupported(fromUnit) || !isUnitSupported(toUnit)) {
            return ERROR_CODE;
        }

        // Проверка относятся ли единицы к одной категории
        if (!areSameCategory(fromUnit, toUnit)) {
            return ERROR_CODE;
        }

        // Вызов соответствующего метода конвертации
        String category = getCategory(fromUnit);
        switch (category) {
            case "Длина":
                return convertLength(value, fromUnit, toUnit);
            case "Вес":
                return convertWeight(value, fromUnit, toUnit);
            case "Температура":
                return convertTemperature(value, fromUnit, toUnit);
            default:
                return ERROR_CODE;
        }
    }

    /**
     * Проверяет, поддерживается ли единица измерения
     */
    private boolean isUnitSupported(String unit) {
        return LENGTH_UNITS.contains(unit) || WEIGHT_UNITS.contains(unit) || TEMP_UNITS.contains(unit);
    }

    /**
     * Проверяет, относятся ли единицы измерения к одной категории
     */
    private boolean areSameCategory(String unit1, String unit2) {
        String category1 = getCategory(unit1);
        String category2 = getCategory(unit2);
        return category1 != null && category1.equals(category2);
    }

    /**
     * Определяет к какой категории относится единица измерения
     */
    private String getCategory(String unit) {
        if (LENGTH_UNITS.contains(unit)) {
            return "Длина";
        } else if (WEIGHT_UNITS.contains(unit)) {
            return "Вес";
        } else if (TEMP_UNITS.contains(unit)) {
            return "Температура";
        }
        return null;
    }

    /**
     * Конвертирует длину
     */
    private double convertLength(double value, String fromUnit, String toUnit) {
        // Конвертация в метры (базовая единица)
        double meters;
        switch (fromUnit) {
            case "Метр":
                meters = value;
                break;
            case "Сантиметр":
                meters = value / 100.0;
                break;
            case "Дюйм":
                meters = value / 39.37;
                break;
            case "Фут":
                meters = value / 3.28;
                break;
            default:
                return ERROR_CODE;
        }

        // Конвертация из метров в целевую единицу
        switch (toUnit) {
            case "Метр":
                return meters;
            case "Сантиметр":
                return meters * 100.0;
            case "Дюйм":
                return meters * 39.37;
            case "Фут":
                return meters * 3.28;
            default:
                return ERROR_CODE;
        }
    }

    /**
     * Конвертирует вес
     */
    private double convertWeight(double value, String fromUnit, String toUnit) {
        // Конвертация в килограммы (базовая единица)
        double kilograms;
        switch (fromUnit) {
            case "Килограмм":
                kilograms = value;
                break;
            case "Грамм":
                kilograms = value / 1000.0;
                break;
            case "Фунт":
                kilograms = value / 2.20462;
                break;
            case "Унция":
                kilograms = value / 35.274;
                break;
            default:
                return ERROR_CODE;
        }

        // Конвертация из килограммов в целевую единицу
        switch (toUnit) {
            case "Килограмм":
                return kilograms;
            case "Грамм":
                return kilograms * 1000.0;
            case "Фунт":
                return kilograms * 2.20462;
            case "Унция":
                return kilograms * 35.274;
            default:
                return ERROR_CODE;
        }
    }

    /**
     * Конвертирует температуру
     */
    private double convertTemperature(double value, String fromUnit, String toUnit) {
        // Если единицы одинаковые
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        // Конвертация через Цельсий как промежуточную единицу
        double celsius;

        // Конвертация из исходной единицы в Цельсий
        switch (fromUnit) {
            case "Цельсий":
                celsius = value;
                break;
            case "Фаренгейт":
                celsius = (value - 32) * 5.0 / 9.0;
                break;
            case "Кельвин":
                celsius = value - 273.15;
                break;
            default:
                return ERROR_CODE;
        }

        // Конвертация из Цельсия в целевую единицу
        switch (toUnit) {
            case "Цельсий":
                return celsius;
            case "Фаренгейт":
                return (celsius * 9.0 / 5.0) + 32;
            case "Кельвин":
                return celsius + 273.15;
            default:
                return ERROR_CODE;
        }
    }

    public static void main(String[] args) {
        UnitConverter converter = new UnitConverter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Конвертер единиц измерения");
        System.out.println("==========================");

        System.out.println("Введите значение:");
        double val = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите исходную единицу (например: Метр, Килограмм, Цельсий):");
        String from = scanner.nextLine();

        System.out.println("Введите целевую единицу:");
        String to = scanner.nextLine();

        double result = converter.convert(val, from, to);
        if (result == ERROR_CODE) {
            System.out.println("Ошибка конвертации! Проверьте правильность введенных единиц.");
        } else {
            System.out.println("Результат: " + result + " " + to);
        }

        scanner.close();
    }
}
