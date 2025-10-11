package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzzForFirst15Numbers() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String[] result = fizzBuzz.generateFizzBuzz(30);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(30);
        assertThat(result[0]).isEqualTo("1");
        assertThat(result[1]).isEqualTo("2");
        assertThat(result[2]).isEqualTo("Fizz");
        assertThat(result[4]).isEqualTo("Buzz");
        assertThat(result[14]).isEqualTo("FizzBuzz");
    }

    @Test
    public void testFizzBuzzWithZeroInput() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String[] result = fizzBuzz.generateFizzBuzz(0);
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    public void testAllFizzValuesAreDivisibleBy3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        int n = 30;
        String[] result = fizzBuzz.generateFizzBuzz(n);
        for (int i = 0; i < result.length; i++) {
            if ("Fizz".equals(result[i])) {
                int value = i + 1;
                assertThat(value % 3).isZero();
                assertThat(value % 5).isNotZero();
            }
        }
    }

    @Test
    public void testAllBuzzValuesAreDivisibleBy5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        int n = 30;
        String[] result = fizzBuzz.generateFizzBuzz(n);
        for (int i = 0; i < result.length; i++) {
            if ("Buzz".equals(result[i])) {
                int value = i + 1;
                assertThat(value % 5).isZero();
                assertThat(value % 3).isNotZero();
            }
        }
    }

    @Test
    public void testAllFizzBuzzValuesAreDivisibleBy3And5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        int n = 60;
        String[] result = fizzBuzz.generateFizzBuzz(n);
        for (int i = 0; i < result.length; i++) {
            if ("FizzBuzz".equals(result[i])) {
                int value = i + 1;
                assertThat(value % 3).isZero();
                assertThat(value % 5).isZero();
            }
        }
    }
}