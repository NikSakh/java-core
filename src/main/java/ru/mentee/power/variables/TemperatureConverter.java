package ru.mentee.power.variables;

public class TemperatureConverter {
    public static void main(String[] args) {
        // Константы
        final double ABSOLUTE_ZERO_KELVIN = 0.0;
        final double ABSOLUTE_ZERO_CELSIUS = -273.15;
        final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;
        final double WATER_FREEZING_C = 0.0;
        final double WATER_BOILING_C = 100.0;

        // Переменные
        double celsius = 25.0;
        double fahrenheit = 77.0;
        double kelvin = 298.15;

        // Конвертация Цельсий в Фаренгейт
        double celsiusToFahrenheit = (celsius * 9/5) + 32;

        // Конвертация Фаренгейт в Цельсий
        double fahrenheitToCelsius = (fahrenheit - 32) * 5/9;

        // Конвертация Цельсий в Кельвин
        double celsiusToKelvin = celsius + 273.15;

        // Конвертация Кельвин в Цельсий
        double kelvinToCelsius = kelvin - 273.15;

        System.out.println("Температурные константы:");
        System.out.println("Абсолютный ноль: " + ABSOLUTE_ZERO_KELVIN + " K, "
                + ABSOLUTE_ZERO_CELSIUS + " °C, "
                + ABSOLUTE_ZERO_FAHRENHEIT + " °F");
        System.out.println("Точки воды: замерзание при " + WATER_FREEZING_C
                + " °C, кипение при " + WATER_BOILING_C + " °C");

        System.out.println("\nИсходные температуры:");
        System.out.println(celsius + " °C = " + celsiusToFahrenheit + " °F");
        System.out.println(fahrenheit + " °F = " + fahrenheitToCelsius + " °C");
        System.out.println(celsius + " °C = " + celsiusToKelvin + " K");
        System.out.println(kelvin + " K = " + kelvinToCelsius + " °C");

        System.out.println("\nПроверка абсолютного нуля:");
        System.out.println(ABSOLUTE_ZERO_CELSIUS + " °C = "
                + (ABSOLUTE_ZERO_CELSIUS + 273.15) + " K");
    }
}
