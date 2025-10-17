package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UnitConverter {

    private static final double ERROR_CODE = -1.0;

    private static final String METER = "Метр";
    private static final String CENTIMETER = "Сантиметр";
    private static final String INCH = "Дюйм";
    private static final String FOOT = "Фут";

    private static final String KILOGRAM = "Килограмм";
    private static final String GRAM = "Грамм";
    private static final String POUND = "Фунт";
    private static final String OUNCE = "Унция";

    private static final String CELSIUS = "Цельсий";
    private static final String FAHRENHEIT = "Фаренгейт";
    private static final String KELVIN = "Кельвин";

    // Conversion factors / constants
    private static final double CENTIMETERS_PER_METER = 100.0;
    private static final double INCHES_PER_METER = 39.37;
    private static final double FEET_PER_METER = 3.28;

    private static final double GRAMS_PER_KILOGRAM = 1000.0;
    private static final double POUNDS_PER_KILOGRAM = 2.20462;
    private static final double OUNCES_PER_KILOGRAM = 35.274;

    private static final double KELVIN_OFFSET = 273.15;

    private static final List<String> LENGTH_UNITS = Arrays.asList(METER, CENTIMETER, INCH, FOOT);
    private static final List<String> WEIGHT_UNITS = Arrays.asList(KILOGRAM, GRAM, POUND, OUNCE);
    private static final List<String> TEMP_UNITS = Arrays.asList(CELSIUS, FAHRENHEIT, KELVIN);

    public double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        if (!isUnitSupported(fromUnit) || !isUnitSupported(toUnit)) {
            return ERROR_CODE;
        }

        if (!areSameCategory(fromUnit, toUnit)) {
            return ERROR_CODE;
        }

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


    private boolean isUnitSupported(String unit) {
        return LENGTH_UNITS.contains(unit) || WEIGHT_UNITS.contains(unit) || TEMP_UNITS.contains(unit);
    }

    private boolean areSameCategory(String unitFirst, String unitSecond) {
        String categoryFirst = getCategory(unitFirst);
        String categorySecond = getCategory(unitSecond);
        return categoryFirst != null && categoryFirst.equals(categorySecond);
    }

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

    private double convertLength(double value, String fromUnit, String toUnit) {
        double meters;
        switch (fromUnit) {
            case METER:
                meters = value;
                break;
            case CENTIMETER:
                meters = value / CENTIMETERS_PER_METER;
                break;
            case INCH:
                meters = value / INCHES_PER_METER;
                break;
            case FOOT:
                meters = value / FEET_PER_METER;
                break;
            default:
                return ERROR_CODE;
        }

        switch (toUnit) {
            case METER:
                return meters;
            case CENTIMETER:
                return meters * CENTIMETERS_PER_METER;
            case INCH:
                return meters * INCHES_PER_METER;
            case FOOT:
                return meters * FEET_PER_METER;
            default:
                return ERROR_CODE;
        }
    }


    private double convertWeight(double value, String fromUnit, String toUnit) {
        double kilograms;
        switch (fromUnit) {
            case KILOGRAM:
                kilograms = value;
                break;
            case GRAM:
                kilograms = value / GRAMS_PER_KILOGRAM;
                break;
            case POUND:
                kilograms = value / POUNDS_PER_KILOGRAM;
                break;
            case OUNCE:
                kilograms = value / OUNCES_PER_KILOGRAM;
                break;
            default:
                return ERROR_CODE;
        }

        switch (toUnit) {
            case KILOGRAM:
                return kilograms;
            case GRAM:
                return kilograms * GRAMS_PER_KILOGRAM;
            case POUND:
                return kilograms * POUNDS_PER_KILOGRAM;
            case OUNCE:
                return kilograms * OUNCES_PER_KILOGRAM;
            default:
                return ERROR_CODE;
        }
    }


    private double convertTemperature(double value, String fromUnit, String toUnit) {
        // Если единицы одинаковые
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        double celsius;

        switch (fromUnit) {
            case CELSIUS:
                celsius = value;
                break;
            case FAHRENHEIT:
                celsius = (value - 32) * 5.0 / 9.0;
                break;
            case KELVIN:
                celsius = value - KELVIN_OFFSET;
                break;
            default:
                return ERROR_CODE;
        }

        switch (toUnit) {
            case CELSIUS:
                return celsius;
            case FAHRENHEIT:
                return (celsius * 9.0 / 5.0) + 32;
            case KELVIN:
                return celsius + KELVIN_OFFSET;
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
        scanner.nextLine();

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