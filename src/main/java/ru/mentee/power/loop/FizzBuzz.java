package ru.mentee.power.loop;

import java.util.stream.IntStream;

public class FizzBuzz {

    private static final int FIZZ_NUMBER = 3;
    private static final int BUZZ_NUMBER = 5;
    private static final int FIZZBUZZ_NUMBER = 15;

    /**
     * Метод возвращает строковое представление чисел от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     * @return массив строк с результатами
     */
    public String[] generateFizzBuzz(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(i -> {
                    if (i % FIZZBUZZ_NUMBER == 0) {
                        return "FizzBuzz";
                    } else if (i % FIZZ_NUMBER == 0) {
                        return "Fizz";
                    } else if (i % BUZZ_NUMBER == 0) {
                        return "Buzz";
                    } else {
                        return String.valueOf(i);
                    }
                })
                .toArray(String[]::new);
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
        fizzBuzz.printFizzBuzz(15);
    }
}