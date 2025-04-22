package ru.mentee.power.variables;

public class ConstantsAndScope {

    public static final double PI = 3.141592653589793;
    private static final int MAX_COUNT = 100;
    protected static final String DEFAULT_NAME = "Unknown";

    public static void main(String[] args) {
        int localVar = 42;
        final String LOCAL_CONST = "Main method constant";

        {
            int blockVar = 100;
            System.out.println("Внутри блока:");
            System.out.println("blockVar = " + blockVar); // Работает - переменная в этой области
            System.out.println("localVar = " + localVar); // Работает - переменная из внешней области
            System.out.println("PI = " + PI); // Работает - константа класса
        }

        // System.out.println(blockVar); // Ошибка! blockVar не видна вне своего блока

        System.out.println("\nВ методе main:");
        System.out.println("localVar = " + localVar);
        System.out.println("LOCAL_CONST = " + LOCAL_CONST);
        System.out.println("PI = " + PI);
        System.out.println("MAX_COUNT = " + MAX_COUNT);

        someMethod();
    }

    public static void someMethod() {
        int methodVar = 123;
        final String METHOD_CONST = "Method constant";

        System.out.println("\nВ методе someMethod:");
        System.out.println("methodVar = " + methodVar);
        System.out.println("METHOD_CONST = " + METHOD_CONST);
        System.out.println("PI = " + PI); // Константы класса доступны

        // System.out.println(localVar); // Ошибка! Не видна - локальная переменная другого метода
    }
}
