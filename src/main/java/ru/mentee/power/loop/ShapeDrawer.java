package ru.mentee.power.loop;

public class ShapeDrawer {

    public String drawSquare(int size) {
        if (size <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("*".repeat(size));
            if (i < size - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public String drawEmptySquare(int size) {
        if (size <= 0) return "";
        if (size == 1) return "*";
        StringBuilder sb = new StringBuilder();
        String full = "*".repeat(size);
        String middle = "*" + " ".repeat(size - 2) + "*";
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                sb.append(full);
            } else {
                sb.append(middle);
            }
            if (i < size - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public String drawTriangle(int height) {
        if (height <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            sb.append("*".repeat(i));
            if (i < height) sb.append("\n");
        }
        return sb.toString();
    }

    public String drawRhombus(int size) {
        if (size <= 0) return "";
        if (size % 2 == 0) return "Размер ромба должен быть нечетным";
        StringBuilder sb = new StringBuilder();
        int mid = size / 2;
        for (int i = 0; i < size; i++) {
            int stars = size - 2 * Math.abs(mid - i);
            int spaces = Math.abs(mid - i);
            sb.append(" ".repeat(spaces));
            sb.append("*".repeat(stars));
            sb.append(" ".repeat(spaces));
            if (i < size - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public void printShape(String shape) {
        System.out.println(shape);
    }

    public static void main(String[] args) {
        ShapeDrawer drawer = new ShapeDrawer();

        System.out.println("Квадрат 5x5:");
        drawer.printShape(drawer.drawSquare(5));

        System.out.println("\nПустой квадрат 5x5:");
        drawer.printShape(drawer.drawEmptySquare(5));

        System.out.println("\nТреугольник высотой 5:");
        drawer.printShape(drawer.drawTriangle(5));

        System.out.println("\nРомб размером 5:");
        drawer.printShape(drawer.drawRhombus(5));
    }
}