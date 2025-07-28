package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmartThermostat {

    private static final double ERROR_TEMP_CODE = -100.0;
    private static final List<String> WEEKDAYS = Arrays.asList(
            "Понедельник", "Вторник", "Среда", "Четверг", "Пятница"
    );
    private static final List<String> WEEKENDS = Arrays.asList(
            "Суббота", "Воскресенье"
    );

    public double getTargetTemperature(int timeOfDay, String dayOfWeek, boolean isOccupied,
                                       double currentOutsideTemperature) {
        // Проверка корректности входных данных
        if (timeOfDay < 0 || timeOfDay > 23 ||
                (!WEEKDAYS.contains(dayOfWeek) && !WEEKENDS.contains(dayOfWeek))) {
            return ERROR_TEMP_CODE;
        }

        double baseTemp;
        boolean isWeekday = WEEKDAYS.contains(dayOfWeek);

        // Определение базовой температуры
        if (isWeekday) {
            if (timeOfDay >= 6 && timeOfDay <= 8) {
                baseTemp = isOccupied ? 22 : 18;
            } else if (timeOfDay >= 9 && timeOfDay <= 17) {
                baseTemp = isOccupied ? 20 : 16;
            } else if (timeOfDay >= 18 && timeOfDay <= 22) {
                baseTemp = isOccupied ? 22 : 17;
            } else {
                baseTemp = isOccupied ? 19 : 16;
            }
        } else {
            if (timeOfDay >= 7 && timeOfDay <= 9) {
                baseTemp = isOccupied ? 23 : 18;
            } else if (timeOfDay >= 10 && timeOfDay <= 17) {
                baseTemp = isOccupied ? 22 : 17;
            } else if (timeOfDay >= 18 && timeOfDay <= 23) {
                baseTemp = isOccupied ? 22 : 17;
            } else {
                baseTemp = isOccupied ? 20 : 16;
            }
        }

        // Применение энергосберегающих правил
        if (currentOutsideTemperature > 25) {
            baseTemp += 1;
        } else if (currentOutsideTemperature < 0) {
            baseTemp -= 1;
        }

        return baseTemp;
    }

    public static void main(String[] args) {
        SmartThermostat thermostat = new SmartThermostat();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите время суток (0-23):");
        int time = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите день недели (например, Понедельник):");
        String day = scanner.nextLine();

        System.out.println("Есть ли кто-то дома? (true/false):");
        boolean occupied = scanner.nextBoolean();

        System.out.println("Введите текущую температуру на улице:");
        double outsideTemp = scanner.nextDouble();

        double targetTemp = thermostat.getTargetTemperature(time, day, occupied, outsideTemp);

        if (targetTemp == ERROR_TEMP_CODE) {
            System.out.println("Ошибка: Некорректные входные данные.");
        } else {
            System.out.println("Рекомендуемая температура: " + targetTemp + "°C");
        }

        scanner.close();
    }
}
