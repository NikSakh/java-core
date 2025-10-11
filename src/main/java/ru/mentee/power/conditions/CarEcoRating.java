package ru.mentee.power.conditions;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CarEcoRating {

    private static final int ERROR_CODE = -1;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 100;
    private static final int EURO_STANDARD_YEAR_THRESHOLD = 2020;

    private static final int BASE_RATING_ELECTRIC = 90;
    private static final int BASE_RATING_HYBRID = 70;
    private static final int BASE_RATING_DIESEL = 40;
    private static final int BASE_RATING_PETROL = 30;

    private static final List<String> VALID_FUEL_TYPES = Arrays.asList("Бензин", "Дизель", "Гибрид", "Электро");

    public int calculateEcoRating(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture,
                                  boolean isEuroCompliant) {
        if (!validateInput(fuelType, engineVolume, fuelConsumption, yearOfManufacture)) {
            return ERROR_CODE;
        }
        int baseRating = getBaseFuelTypeRating(fuelType);
        if (baseRating == ERROR_CODE) {
            return ERROR_CODE;
        }
        int rating = applyRatingModifiers(baseRating, fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);
        return clampRating(rating);
    }

    private boolean validateInput(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture) {
        if (!VALID_FUEL_TYPES.contains(fuelType)) {
            return false;
        }
        if (engineVolume < 0 || fuelConsumption < 0) {
            return false;
        }
        int currentYear = Year.now().getValue();
        if (yearOfManufacture > currentYear) {
            return false;
        }
        if (fuelType.equals("Электро") && engineVolume != 0) {
            return false;
        }
        return true;
    }

    private int getBaseFuelTypeRating(String fuelType) {
        switch (fuelType) {
            case "Электро": return BASE_RATING_ELECTRIC;
            case "Гибрид": return BASE_RATING_HYBRID;
            case "Дизель": return BASE_RATING_DIESEL;
            case "Бензин": return BASE_RATING_PETROL;
            default: return ERROR_CODE;
        }
    }

    private int applyRatingModifiers(int baseRating, String fuelType, double engineVolume,
                                     double fuelConsumption, int yearOfManufacture,
                                     boolean isEuroCompliant) {
        double rating = baseRating;
        int yearPenalty = Math.max(0, yearOfManufacture < EURO_STANDARD_YEAR_THRESHOLD
                ? EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture
                : 0);
        if (fuelType.equals("Электро")) {
            rating -= fuelConsumption * 0.5;
            // yearPenalty
            rating -= (yearOfManufacture < EURO_STANDARD_YEAR_THRESHOLD)
                    ? EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture : 0;
            // isEuroCompliant не применяется
        } else if (fuelType.equals("Гибрид")) {
            rating -= engineVolume * 5;
            rating -= fuelConsumption * 2;
            rating -= (yearOfManufacture < EURO_STANDARD_YEAR_THRESHOLD)
                    ? EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture : 0;
            if (isEuroCompliant) {
                rating += 10;
            }
            if (fuelConsumption < 5) {
                rating += 15;
            }
        } else if (fuelType.equals("Дизель") || fuelType.equals("Бензин")) {
            rating -= engineVolume * 5;
            rating -= fuelConsumption * 2;
            rating -= (yearOfManufacture < EURO_STANDARD_YEAR_THRESHOLD)
                    ? EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture : 0;
            if (isEuroCompliant) {
                rating += 10;
            }
        }
        return (int)Math.round(rating);
    }

    private int clampRating(int rating) {
        return Math.max(MIN_RATING, Math.min(MAX_RATING, rating));
    }

    public static void main(String[] args) {
        CarEcoRating ecoRating = new CarEcoRating();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Тип топлива (Бензин, Дизель, Гибрид, Электро):");
        String type = scanner.next();
        System.out.println("Объем двигателя (л, для Электро - 0):");
        double volume = scanner.nextDouble();
        System.out.println("Расход (л/100км или кВтч/100км):");
        double consumption = scanner.nextDouble();
        System.out.println("Год выпуска:");
        int year = scanner.nextInt();
        System.out.println("Соответствует стандарту Евро-6? (true/false):");
        boolean isEuro = scanner.nextBoolean();

        int rating = ecoRating.calculateEcoRating(type, volume, consumption, year, isEuro);
        if (rating == ERROR_CODE) {
            System.out.println("Ошибка во входных данных!");
        } else {
            System.out.println("Эко-рейтинг автомобиля: " + rating);
        }

        scanner.close();
    }
}