package ru.mentee.power.conditions;

import java.util.Scanner;

public class CreditCalculator {

    // Константы для базовых процентных ставок
    private static final double MORTGAGE_RATE = 9.0;
    private static final double CONSUMER_RATE = 15.0;
    private static final double CAR_LOAN_RATE = 12.0;

    // Константы для диапазонов кредитного рейтинга
    private static final int MIN_CREDIT_SCORE = 300;
    private static final int MAX_CREDIT_SCORE = 850;
    private static final int EXCELLENT_SCORE = 750;
    private static final int GOOD_SCORE = 650;
    private static final int FAIR_SCORE = 500;

    // Константы для суммы и срока кредита
    private static final double MIN_LOAN_AMOUNT = 10_000;
    private static final double MAX_LOAN_AMOUNT = 10_000_000;
    private static final int MIN_LOAN_TERM = 1;
    private static final int MAX_LOAN_TERM = 360;

    /**
     * Рассчитывает ежемесячный платеж по кредиту
     */
    public double calculateMonthlyPayment(double loanAmount, int loanTermMonths, String creditType, int creditScore) {
        // Проверка входных данных
        if (loanAmount < MIN_LOAN_AMOUNT || loanAmount > MAX_LOAN_AMOUNT) {
            return -1;
        }

        if (loanTermMonths < MIN_LOAN_TERM || loanTermMonths > MAX_LOAN_TERM) {
            return -1;
        }

        if (creditScore < MIN_CREDIT_SCORE || creditScore > MAX_CREDIT_SCORE) {
            return -1;
        }

        // Определение базовой ставки
        double baseRate;
        switch (creditType) {
            case "Ипотека":
                baseRate = MORTGAGE_RATE;
                break;
            case "Потребительский":
                baseRate = CONSUMER_RATE;
                break;
            case "Автокредит":
                baseRate = CAR_LOAN_RATE;
                break;
            default:
                return -1;
        }

        // Корректировка ставки в зависимости от кредитного рейтинга
        double adjustedRate = baseRate;
        if (creditScore >= EXCELLENT_SCORE) {
            adjustedRate -= 2;
        } else if (creditScore >= GOOD_SCORE) {
            adjustedRate -= 1;
        } else if (creditScore < FAIR_SCORE) {
            adjustedRate += 3;
        }

        // Расчет месячной ставки
        double monthlyRate = adjustedRate / 12 / 100;

        // Расчет ежемесячного платежа
        double monthlyPayment = loanAmount *
                (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths)) /
                (Math.pow(1 + monthlyRate, loanTermMonths) - 1);

        return monthlyPayment;
    }

    public static void main(String[] args) {
        CreditCalculator calculator = new CreditCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Калькулятор кредита");

        System.out.print("Введите сумму кредита (от 10,000 до 10,000,000 руб.): ");
        double amount = scanner.nextDouble();

        System.out.print("Введите срок кредита в месяцах (от 1 до 360): ");
        int term = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.print("Введите тип кредита (Ипотека/Потребительский/Автокредит): ");
        String type = scanner.nextLine();

        System.out.print("Введите кредитный рейтинг (300-850): ");
        int score = scanner.nextInt();

        double payment = calculator.calculateMonthlyPayment(amount, term, type, score);

        if (payment == -1) {
            System.out.println("Ошибка: некорректные входные данные");
        } else {
            System.out.printf("Ежемесячный платеж: %.2f руб.%n", payment);
        }

        scanner.close();
    }
}
