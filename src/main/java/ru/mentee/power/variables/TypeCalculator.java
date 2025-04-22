package ru.mentee.power.variables;

public class TypeCalculator {
    public static void main(String[] args) {
        byte byteVar = 10;
        short shortVar = 100;
        int intVar = 1000;
        long longVar = 10000L;

        float floatVar = 3.14f;
        double doubleVar = 2.71828;

        int intPlusByte = intVar + byteVar;
        long longPlusShort = longVar + shortVar;
        int intPlusShort = intVar + shortVar;

        double doublePlusFloat = doubleVar + floatVar;
        float floatPlusInt = floatVar + intVar;
        double doublePlusLong = doubleVar + longVar;

        double doublePlusInt = doubleVar + intVar;
        long longPlusInt = longVar + intVar;

        System.out.println("Results:");
        System.out.println("int + byte: " + intPlusByte + " (int)");
        System.out.println("long + short: " + longPlusShort + " (long)");
        System.out.println("int + short: " + intPlusShort + " (int)");
        System.out.println();
        System.out.println("double + float: " + doublePlusFloat + " (double)");
        System.out.println("float + int: " + floatPlusInt + " (float)");
        System.out.println("double + long: " + doublePlusLong + " (double)");
        System.out.println();
        System.out.println("double + int: " + doublePlusInt + " (double)");
        System.out.println("long + int: " + longPlusInt + " (long)");
    }
}
