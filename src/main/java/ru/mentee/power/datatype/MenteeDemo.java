package ru.mentee.power.datatype;

public class MenteeDemo {
    public static void main(String[] args) {
        // Создаем несколько менти с разными данными
        Mentee mentee1 = new Mentee("Иван", "Иванов", 20, 4.8, 2);
        Mentee mentee2 = new Mentee("Петр", "Петров", 21, 3.9, 3);
        Mentee mentee3 = new Mentee("Анна", "Сидорова", 19, 5.0, 1);
        Mentee mentee4 = new Mentee("Елена", "Кузнецова", 22, 3.2, 4);

        // Массив для удобного перебора
        Mentee[] mentees = {mentee1, mentee2, mentee3, mentee4};

        // Выводим информацию о каждом менти
        System.out.println("=== Информация о менти ===");
        for (Mentee mentee : mentees) {
            mentee.displayInfo();
            System.out.println("Отличник: " + (mentee.isExcellent() ? "Да" : "Нет"));
            System.out.println("Стипендия: " + mentee.calculateScholarship() + " руб.");
            System.out.println();
        }

        // Проверяем, кто из менти является отличником
        System.out.println("=== Отличники ===");
        for (Mentee mentee : mentees) {
            if (mentee.isExcellent()) {
                System.out.println(mentee.getFirstName() + " " + mentee.getLastName() +
                        " - отличник (средний балл: " + mentee.getAverageGrade() + ")");
            }
        }

        // Повышаем менти на следующий уровень
        System.out.println("\n=== Повышение уровня ===");
        for (Mentee mentee : mentees) {
            boolean leveledUp = mentee.advanceToNextLevel();
            System.out.print(mentee.getFirstName() + " " + mentee.getLastName() +
                    ": уровень " + mentee.getLevel());
            if (leveledUp) {
                System.out.println(" -> повышен до " + mentee.getLevel());
            } else {
                System.out.println(" -> уже максимальный уровень");
            }
        }

        // Выводим обновленную информацию после повышения уровня
        System.out.println("\n=== Обновленная информация ===");
        for (Mentee mentee : mentees) {
            mentee.displayInfo();
            System.out.println();
        }
    }
}