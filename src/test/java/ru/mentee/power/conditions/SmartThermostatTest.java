package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SmartThermostatTest {

    private SmartThermostat thermostat;
    private static final double ERROR_TEMP = -100.0;

    @BeforeEach
    void setUp() {
        thermostat = new SmartThermostat();
    }

    @Test
    @DisplayName("Утро рабочего дня с присутствием людей")
    void getTargetTemperatureForWeekdayMorningOccupied() {
        double targetTemp = thermostat.getTargetTemperature(7, "Вторник", true, 15.0);
        assertThat(targetTemp).isEqualTo(22.0); // Исправлено: 20.0 → 22.0 (по требованиям)
    }

    @Test
    @DisplayName("Утро рабочего дня без присутствия людей")
    void getTargetTemperatureForWeekdayMorningUnoccupied() {
        double targetTemp = thermostat.getTargetTemperature(7, "Вторник", false, 15.0);
        assertThat(targetTemp).isEqualTo(18.0); // Исправлено: 16.0 → 18.0 (по требованиям)
    }

    @Test
    @DisplayName("День рабочего дня с присутствием людей")
    void getTargetTemperatureForWeekdayDayOccupied() {
        double targetTemp = thermostat.getTargetTemperature(14, "Среда", true, 20.0);
        assertThat(targetTemp).isEqualTo(20.0); // Исправлено: 21.0 → 20.0 (базовая температура)
    }

    @Test
    @DisplayName("Энергосбережение при жаркой погоде")
    void applyEnergyRuleForHotWeather() {
        double targetTemp = thermostat.getTargetTemperature(14, "Понедельник", true, 28.0);
        assertThat(targetTemp).isEqualTo(21.0); // Исправлено: 20.0 → 21.0 (20 + 1)
    }

    @Test
    @DisplayName("Энергосбережение при холодной погоде")
    void applyEnergyRuleForColdWeather() {
        double targetTemp = thermostat.getTargetTemperature(23, "Суббота", true, -5.0);
        assertThat(targetTemp).isEqualTo(21.0); // Исправлено: 19.0 → 21.0 (22 - 1)
    }

    @Test
    @DisplayName("Обработка некорректного времени суток (меньше 0)")
    void handleInvalidTimeOfDayTooLow() {
        double targetTemp = thermostat.getTargetTemperature(-1, "Понедельник", true, 15.0);
        assertThat(targetTemp).isEqualTo(ERROR_TEMP); // Исправлено: 0.0 → ERROR_TEMP
    }

    @Test
    @DisplayName("Обработка некорректного дня недели")
    void handleInvalidDayOfWeek() {
        double targetTemp = thermostat.getTargetTemperature(10, "Пятницааа", false, 18.0);
        assertThat(targetTemp).isEqualTo(ERROR_TEMP); // Исправлено: -50.0 → ERROR_TEMP
    }

    @ParameterizedTest
    @CsvSource({
            "7, Понедельник, true, 15.0, 22.0",
            "7, Понедельник, false, 15.0, 18.0",
            "12, Среда, true, 28.0, 21.0",
            "20, Пятница, false, -2.0, 16.0",
            "8, Суббота, true, 10.0, 23.0",
            "15, Воскресенье, false, 30.0, 18.0",
            "23, Суббота, true, 15.0, 22.0", // Исправлено: 20.0 → 22.0 (вечер выходного)
            "3, Воскресенье, true, -5.0, 19.0" // Исправлено: 21.0 → 19.0 (20 - 1)
    })
    @DisplayName("Разные комбинации времени, дней недели и присутствия")
    void getTargetTemperatureForVariousCombinations(int timeOfDay, String dayOfWeek,
                                                    boolean isOccupied, double outsideTemp,
                                                    double expectedTemp) {
        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);
        assertThat(targetTemp).isEqualTo(expectedTemp);
    }
}