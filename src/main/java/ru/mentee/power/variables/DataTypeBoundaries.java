package ru.mentee.power.variables;

public class DataTypeBoundaries {
    public static void main(String[] args) {
        // Выводим минимальные и максимальные значения для всех числовых типов данных
        System.out.println("byte: от " + Byte.MIN_VALUE + " до " + Byte.MAX_VALUE);
        System.out.println("short: от " + Short.MIN_VALUE + " до " + Short.MAX_VALUE);
        System.out.println("int: от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
        System.out.println("long: от " + Long.MIN_VALUE + " до " + Long.MAX_VALUE);
        System.out.println("float: от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE);
        System.out.println("double: от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE);

        // Демонстрация переполнения для типа byte
        byte maxByte = Byte.MAX_VALUE;
        System.out.println("\nДемонстрация переполнения byte:");
        System.out.println("Максимальное значение byte: " + maxByte);
        System.out.println("После добавления 1: " + (byte)(maxByte + 1));
    }
}
