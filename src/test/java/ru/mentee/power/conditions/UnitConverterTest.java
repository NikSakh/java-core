package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnitConverterTest {

    private UnitConverter converter;
    private static final double DELTA = 0.001;
    private static final double ERROR = -1.0;

    @BeforeEach
    void setUp() {
        converter = new UnitConverter();
    }

    @Test
    @DisplayName("Конвертация из метров в сантиметры")
    void convertMetresToCentimetres() {
        double value = 1.0;
        String fromUnit = "Метр";
        String toUnit = "Сантиметр";

        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(100.0, within(DELTA));
    }

    @Test
    @DisplayName("Конвертация из сантиметров в метры")
    void convertCentimetresToMetres() {

        double value = 150.0;
        String fromUnit = "Сантиметр";
        String toUnit = "Метр";

        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(1.5, within(DELTA));
    }

    @Test
    @DisplayName("Конвертация из метров в футы")
    void convertMetresToFeet() {
        double value = 2.0;
        String fromUnit = "Метр";
        String toUnit = "Фут";
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(6.56, within(DELTA));
    }

    @Test
    @DisplayName("Конвертация из килограммов в граммы")
    void convertKilogramsToGrams() {
        double value = 2.5;
        String fromUnit = "Килограмм";
        String toUnit = "Грамм";
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(2500.0, within(DELTA));
    }

    @Test
    @DisplayName("Конвертация из фунтов в унции")
    void convertPoundsToOunces() {
        double value = 1.0; // 1 фунт
        String fromUnit = "Фунт";
        String toUnit = "Унция";
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(16.0, within(0.1)); // Увеличиваем погрешность из-за приблизительного значения
    }

    @Test
    @DisplayName("Конвертация из Цельсия в Фаренгейт")
    void convertCelsiusToFahrenheit() {
        double value = 25.0;
        String fromUnit = "Цельсий";
        String toUnit = "Фаренгейт";

        double convertedValue = converter.convert(value, fromUnit, toUnit);

        double expectedValue = (25.0 * 9.0 / 5.0) + 32.0;
        assertThat(convertedValue).isCloseTo(expectedValue, within(DELTA));
    }

    @Test
    @DisplayName("Конвертация из Фаренгейта в Кельвин")
    void convertFahrenheitToKelvin() {
        double value = 32.0; // 32°F = 0°C = 273.15K
        String fromUnit = "Фаренгейт";
        String toUnit = "Кельвин";
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        assertThat(convertedValue).isCloseTo(273.15, within(DELTA));
    }

    @Test
    @DisplayName("Обработка несовместимых единиц измерения")
    void handleIncompatibleUnits() {
        double value = 10.0;
        String fromUnit = "Метр";
        String toUnit = "Килограмм";

        double result = converter.convert(value, fromUnit, toUnit);

        assertThat(result).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Обработка неподдерживаемых единиц измерения (fromUnit)")
    void handleUnsupportedFromUnit() {
        double value = 10.0;
        String fromUnit = "Миля"; // Неподдерживаемая единица
        String toUnit = "Метр";

        double result = converter.convert(value, fromUnit, toUnit);

        assertThat(result).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Обработка неподдерживаемых единиц измерения (toUnit)")
    void handleUnsupportedToUnit() {
        double value = 10.0;
        String fromUnit = "Метр";
        String toUnit = "Ярд";

        double result = converter.convert(value, fromUnit, toUnit);

        assertThat(result).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Обработка null в fromUnit")
    void handleNullFromUnit() {
        double value = 10.0;
        String fromUnit = null;
        String toUnit = "Метр";

        assertThrows(NullPointerException.class, () -> converter.convert(value, fromUnit, toUnit));
    }

    @ParameterizedTest
    @CsvSource({ // value, fromUnit, toUnit, expected
            "1.0, Метр, Сантиметр, 100.0",
            "100.0, Сантиметр, Метр, 1.0",
            "1.0, Килограмм, Фунт, 2.20462",
            "1.0, Фунт, Килограмм, 0.45359", // 1 / 2.20462
            "0.0, Цельсий, Фаренгейт, 32.0",
            "273.15, Кельвин, Цельсий, 0.0",
            // Исправленные значения:
            "5.0, Метр, Дюйм, 196.85", // 5 метров = 5 * 39.37 = 196.85 дюймов
            "10.0, Килограмм, Унция, 352.74" // 10 кг = 10 * 35.274 = 352.74 унций
    })
    @DisplayName("Различные конвертации")
    void testVariousConversions(double value, String fromUnit, String toUnit, double expected) {
        double convertedValue = converter.convert(value, fromUnit, toUnit);
        assertThat(convertedValue).isCloseTo(expected, within(DELTA));
    }
}