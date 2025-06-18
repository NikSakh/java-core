package ru.mentee.power.variables;

public class DataTypesInAction {
    public static void main(String[] args) {
        // Создаем переменные различных типов
        int intNum1 = 10;
        int intNum2 = 3;
        double doubleNum = 3.0;
        float floatNum = 2.5f;
        boolean bool1 = true;
        boolean bool2 = false;
        char char1 = 'A';
        char char2 = 66; // ASCII код для 'B'
        byte byteNum = 100;
        short shortNum = 1000;
        long longNum = 1000000L;

        System.out.println("1. Деление целых чисел с отбрасыванием дробной части:");
        int intDivision = intNum1 / intNum2;
        System.out.println(intNum1 + " / " + intNum2 + " = " + intDivision +
                " (дробная часть отброшена)");

        System.out.println("\n2. Деление целого числа на число с плавающей точкой:");
        double mixedDivision = intNum1 / doubleNum;
        System.out.println(intNum1 + " / " + doubleNum + " = " + mixedDivision +
                " (результат автоматически приведен к double)");

        System.out.println("\n3. Явное приведение типов:");
        // Приведение double к int (отбрасывание дробной части)
        int castDoubleToInt = (int)doubleNum;
        System.out.println("(int)" + doubleNum + " = " + castDoubleToInt);

        // Приведение int к char
        char castIntToChar = (char)65;
        System.out.println("(char)65 = " + castIntToChar);

        // Приведение long к int (возможна потеря данных)
        int castLongToInt = (int)longNum;
        System.out.println("(int)" + longNum + " = " + castLongToInt);

        System.out.println("\n4. Автоматическое повышение типа при операциях:");
        // int + double = double
        System.out.println(intNum1 + " + " + doubleNum + " = " + (intNum1 + doubleNum) +
                " (тип результата: double)");

        // byte + short = int
        System.out.println(byteNum + " + " + shortNum + " = " + (byteNum + shortNum) +
                " (тип результата: int)");

        System.out.println("\n5. Операции с boolean значениями:");
        System.out.println(bool1 + " && " + bool2 + " = " + (bool1 && bool2));
        System.out.println(bool1 + " || " + bool2 + " = " + (bool1 || bool2));
        System.out.println("!" + bool1 + " = " + (!bool1));

        System.out.println("\n6. Работа с символами (char) и их кодами:");
        System.out.println("char1: " + char1 + " (код: " + (int)char1 + ")");
        System.out.println("char2: " + char2 + " (код: " + (int)char2 + ")");
        System.out.println("char1 + char2 = " + (char1 + char2) +
                " (сумма кодов, тип результата: int)");
        System.out.println("char1 + \"\" + char2 = " + char1 + "" + char2 +
                " (конкатенация строк)");
    }
}
