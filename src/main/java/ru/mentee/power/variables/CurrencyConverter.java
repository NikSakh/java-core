package ru.mentee.power.variables;

public class CurrencyConverter {
    public static void main(String[] args) {
        // Объявляем константы с курсами валют (используем final)
        final double USD_TO_RUB = 90.50;  // 1 доллар = 90.50 руб
        final double EUR_TO_RUB = 98.75;  // 1 евро = 98.75 руб
        final double CNY_TO_RUB = 12.60;  // 1 юань = 12.60 руб

        // Создаем переменные для сумм в различных валютах
        double rubles = 10000.0;
        double dollars = 100.0;
        double euros = 50.0;
        double yuan = 500.0;

        // Реализуем конвертацию между валютами
        double rubToUsd = rubles / USD_TO_RUB;
        double rubToEur = rubles / EUR_TO_RUB;
        double rubToCny = rubles / CNY_TO_RUB;

        double usdToRub = dollars * USD_TO_RUB;
        double eurToRub = euros * EUR_TO_RUB;
        double cnyToRub = yuan * CNY_TO_RUB;

        // Демонстрируем проблему точности при работе с double
        double sum = 0.1 + 0.2;
        boolean isEqual = (sum == 0.3);

        // Выводим все результаты конвертации
        System.out.println("Конвертация валют:");
        System.out.printf("%.2f RUB = %.2f USD\n", rubles, rubToUsd);
        System.out.printf("%.2f RUB = %.2f EUR\n", rubles, rubToEur);
        System.out.printf("%.2f RUB = %.2f CNY\n", rubles, rubToCny);
        System.out.printf("%.2f USD = %.2f RUB\n", dollars, usdToRub);
        System.out.printf("%.2f EUR = %.2f RUB\n", euros, eurToRub);
        System.out.printf("%.2f CNY = %.2f RUB\n", yuan, cnyToRub);

        // Выводим демонстрацию проблемы точности
        System.out.println("\nПроблема точности с double:");
        System.out.println("0.1 + 0.2 = " + sum);
        System.out.println("0.1 + 0.2 == 0.3? " + isEqual);
        System.out.println("Рекомендация: для финансовых расчетов используйте BigDecimal");
    }
}
