package ru.mentee.power.variables;

public class StringOperations {
    public static void main(String[] args) {
    String str1 = "Hello";
    String str2 = "World";
    char ch1 = '!';
    char ch2 = 'A';
    int number = 42;

    String concatenated = str1 + " " + str2 + ch1;
    System.out.println("Конкатенация: " + concatenated);

    String charToString = Character.toString(ch2);
    System.out.println("Символ в строку: " + charToString);

    String numberToString = Integer.toString(number);
    System.out.println("Число в строку: " + numberToString);

    int stringToNumber = Integer.parseInt(numberToString);
    System.out.println("Строка в число: " + stringToNumber);

    char extractedChar = str1.charAt(1);
    System.out.println("Второй символ строки '" + str1 + "': " + extractedChar);
    }
}
