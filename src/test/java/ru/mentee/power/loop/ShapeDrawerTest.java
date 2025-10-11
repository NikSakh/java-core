package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShapeDrawerTest {

    private final ShapeDrawer drawer = new ShapeDrawer();

    @Test
    void testDrawSquare() {
        String expected = "***\n***\n***";
        String result = drawer.drawSquare(3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawEmptySquare() {
        String expected = "***\n* *\n***";
        String result = drawer.drawEmptySquare(3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawTriangle() {
        String expected = "*\n**\n***";
        String result = drawer.drawTriangle(3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawRhombus() {
        String expected = " * \n***\n * ";
        String result = drawer.drawRhombus(3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testWithZeroOrNegativeSize() {
        assertThat(drawer.drawSquare(0)).isEmpty();
        assertThat(drawer.drawEmptySquare(-1)).isEmpty();
        assertThat(drawer.drawTriangle(0)).isEmpty();
        assertThat(drawer.drawRhombus(-2)).isEmpty();
    }

    @Test
    void testWithLargeSize() {
        String result = drawer.drawSquare(10);
        String[] lines = result.split("\n");
        assertThat(lines).hasSize(10);
        for (String line : lines) {
            assertThat(line).hasSize(10);
            assertThat(line.chars().allMatch(c -> c == '*')).isTrue();
        }
    }

    @Test
    void testRhombusWithEvenSize() {
        String result = drawer.drawRhombus(4);
        assertThat(result).contains("Размер ромба должен быть нечетным");
    }
}