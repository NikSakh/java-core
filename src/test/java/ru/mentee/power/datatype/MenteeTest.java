package ru.mentee.power.datatype;

import org.junit.jupiter.api.Test;
import ru.mentee.power.datatype.Mentee;

import static org.junit.jupiter.api.Assertions.*;

public class MenteeTest {

    @Test
    public void testMenteeCreation() {
        Mentee mentee = new Mentee("Иван", "Иванов", 20, 4.2, 2);

        assertEquals("Иван", mentee.getFirstName());
        assertEquals("Иванов", mentee.getLastName());
        assertEquals(20, mentee.getAge());
        assertEquals(4.2, mentee.getAverageGrade(), 0.001);
        assertEquals(2, mentee.getLevel());
    }

    @Test
    public void testIsExcellent() {
        Mentee excellentMentee = new Mentee("Мария", "Петрова", 19, 4.8, 1);
        Mentee averageMentee = new Mentee("Петр", "Сидоров", 21, 3.9, 3);

        assertTrue(excellentMentee.isExcellent());
        assertFalse(averageMentee.isExcellent());
    }

    @Test
    public void testAdvanceToNextLevel() {
        Mentee junior = new Mentee("Алексей", "Смирнов", 18, 4.0, 1);
        Mentee senior = new Mentee("Ольга", "Козлова", 22, 4.7, 5);

        assertTrue(junior.advanceToNextLevel());
        assertEquals(2, junior.getLevel());

        assertFalse(senior.advanceToNextLevel());
        assertEquals(5, senior.getLevel());
    }

    @Test
    public void testInvalidLevelValue() {
        Mentee mentee1 = new Mentee("Дмитрий", "Попов", 19, 4.3, 0);
        Mentee mentee2 = new Mentee("Анна", "Соколова", 20, 4.5, 6);

        assertEquals(1, mentee1.getLevel());
        assertEquals(1, mentee2.getLevel());
    }

    @Test
    public void testCalculateScholarship() {
        // Отличник (≥4.5) - 2000 рублей
        Mentee excellentMentee = new Mentee("Мария", "Иванова", 19, 4.8, 1);
        assertEquals(2000, excellentMentee.calculateScholarship());

        // Граничное значение отличника (ровно 4.5)
        Mentee borderlineExcellent = new Mentee("Иван", "Иванов", 20, 4.5, 2);
        assertEquals(2000, borderlineExcellent.calculateScholarship());

        // Хорошист (≥4.0 и <4.5) - 1500 рублей
        Mentee goodMentee = new Mentee("Алексей", "Смирнов", 18, 4.2, 1);
        assertEquals(1500, goodMentee.calculateScholarship());

        // Граничное значение хорошиста (ровно 4.0)
        Mentee borderlineGood = new Mentee("Елена", "Петрова", 21, 4.0, 3);
        assertEquals(1500, borderlineGood.calculateScholarship());

        // Средний балл (<4.0) - 1000 рублей
        Mentee averageMentee = new Mentee("Петр", "Сидоров", 21, 3.9, 3);
        assertEquals(1000, averageMentee.calculateScholarship());

        // Низкий балл
        Mentee poorMentee = new Mentee("Ольга", "Козлова", 22, 2.5, 5);
        assertEquals(1000, poorMentee.calculateScholarship());
    }
}