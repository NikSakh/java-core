package ru.mentee.power.variables;

public class DataTypes {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы с типами данных в Java ===");

        // 1. Объявление переменных примитивных типов
        byte byteVar = 127;                    // 8-битное целое число
        short shortVar = 32767;                // 16-битное целое число
        int intVar = 2147483647;               // 32-битное целое число
        long longVar = 9223372036854775807L;   // 64-битное целое число
        float floatVar = 3.4028235e38f;        // 32-битное число с плавающей точкой
        double doubleVar = 1.7976931348623157e308; // 64-битное число с плавающей точкой
        char charVar = 'A';                    // 16-битный символ Unicode
        boolean booleanVar = true;             // Логическое значение true/false

        // 2. Создание строк и массивов
        String string1 = "Hello";
        String string2 = "Java";
        int[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"один", "два", "три"};

        // 3. Математические операции с разными типами данных
        int sumInt = intVar + 10;
        double sumDouble = doubleVar + floatVar;
        float divFloat = floatVar / 2.5f;
        long multLong = longVar / 2;
        char nextChar = (char)(charVar + 1);

        // 4. Преобразование типов
        // Автоматическое преобразование (расширение)
        int autoConverted = byteVar;
        // Явное приведение (сужение)
        byte explicitConverted = (byte)shortVar;

        // 5. Вывод результатов
        System.out.println("\n=== Примитивные типы ===");
        System.out.println("byte: " + byteVar);
        System.out.println("short: " + shortVar);
        System.out.println("int: " + intVar);
        System.out.println("long: " + longVar);
        System.out.println("float: " + floatVar);
        System.out.println("double: " + doubleVar);
        System.out.println("char: " + charVar + " (код: " + (int)charVar + ")");
        System.out.println("boolean: " + booleanVar);

        System.out.println("\n=== Строки и массивы ===");
        System.out.println("Строка 1: " + string1);
        System.out.println("Строка 2: " + string2);
        System.out.println("Конкатенация: " + string1 + " " + string2);
        System.out.print("Массив int: ");
        for (int num : intArray) System.out.print(num + " ");
        System.out.print("\nМассив String: ");
        for (String str : stringArray) System.out.print(str + " ");

        System.out.println("\n\n=== Математические операции ===");
        System.out.println("int + 10 = " + sumInt);
        System.out.println("double + float = " + sumDouble);
        System.out.println("float / 2.5 = " + divFloat);
        System.out.println("long / 2 = " + multLong);
        System.out.println("char + 1 = " + nextChar + " (код: " + (int)nextChar + ")");

        System.out.println("\n=== Преобразование типов ===");
        System.out.println("Автоматическое byte в int: " + autoConverted);
        System.out.println("Явное short в byte: " + explicitConverted);

    }
}
