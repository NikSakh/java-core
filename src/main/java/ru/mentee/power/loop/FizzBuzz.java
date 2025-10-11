package ru.mentee.power.loop;

public class FizzBuzz {

    /**
     * Метод возвращает строковое представление чисел от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     * @return массив строк с результатами
     */
    public String[] generateFizzBuzz(int n) {
        String[] result = new String[n];
        for (int index = 1; index <= n; index++) {
            if (index % 3 == 0 && index % 5 == 0) {
                result[index-1] = "FizzBuzz";
            } else if (index % 3 == 0) {
                result[index-1] = "Fizz";
            } else if (index % 5 == 0) {
                result[index-1] = "Buzz";
            } else {
                result[index-1] = String.valueOf(index);
            }
        }
        return result;
    }

    /**
     * Метод выводит на экран числа от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     */
    public void printFizzBuzz(int n) {
        String[] results = generateFizzBuzz(n);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        System.out.println("FizzBuzz для чисел от 1 до 15:");
        fizzBuzz.printFizzBuzz(30);
    }
}