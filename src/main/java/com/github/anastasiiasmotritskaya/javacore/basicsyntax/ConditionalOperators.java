package com.github.anastasiiasmotritskaya.javacore.basicsyntax;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Работа с условными операторами
 * Методы: перевод оценок из численного формата в буквенные, определитель времени года, калькулятор скидок
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class ConditionalOperators {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private ConditionalOperators() {

    }

    /**
     * Конвертирует числовую оценку в буквенную
     * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: 0-59
     *
     * @param score количество баллов
     * @return char буквенная оценка
     * {@code char result = convertToGrade_if(95);} // A
     * @throws IllegalArgumentException если:
     *                                  <ul>
     *                                   <li>score больше 100 баллов</li>
     *                                   <li>score меньше 0 баллов</li>
     *                                  </ul>
     */
    public static char convertToGrade_if(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }

    /**
     * Конвертирует числовую оценку в буквенную
     * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: 0-59
     *
     * @param score количество баллов
     * @return char буквенная оценка
     * {@code char result = convertToGrade_switch(95);} // A
     * @throws IllegalArgumentException если:
     *                                  <ul>
     *                                   <li>score больше 100 баллов</li>
     *                                   <li>score меньше 0 баллов</li>
     *                                  </ul>
     */
    public static char convertToGrade_switch(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        switch (score / 10) {
            case 10:
            case 9:
                return 'A';
            case 8:
                return 'B';
            case 7:
                return 'C';
            case 6:
                return 'D';
            default:
                return 'F';
        }
    }

    /**
     * Конвертирует числовую оценку в буквенную
     * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: 0-59
     *
     * @param score количество баллов
     * @return char буквенная оценка
     * {@code char result = convertToGrade_switch_exp(95);} // A
     * @throws IllegalArgumentException если:
     *                                  <ul>
     *                                   <li>score больше 100 баллов</li>
     *                                   <li>score меньше 0 баллов</li>
     *                                  </ul>
     */
    public static char convertToGrade_switch_exp(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        return switch (score) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> 'A';
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> 'B';
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> 'C';
            case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> 'D';
            default -> 'F';
        };
    }

    /**
     * Определяет время года по номеру месяца
     * Зима: 12,1,2; Весна: 3,4,5; Лето: 6,7,8; Осень: 9,10,11
     *
     * @param month номер месяца
     * @return String сезон
     * {@code String season = getSeason_if(11);} // Autumn
     * @throws IllegalArgumentException если month меньше 1 или month больше 12
     */
    public static String getSeason_if(int month) {
        if (month == 12 || month == 1 || month == 2) return "Winter";
        else if (month == 3 || month == 4 || month == 5) return "Spring";
        else if (month == 6 || month == 7 || month == 8) return "Summer";
        else if (month == 9 || month == 10 || month == 11) return "Autumn";
        else
            throw new IllegalArgumentException("The month number is entered incorrectly! The month number must be between 1 and 12.");
    }

    /**
     * Определяет время года по номеру месяца
     * Зима: 12,1,2; Весна: 3,4,5; Лето: 6,7,8; Осень: 9,10,11
     *
     * @param month номер месяца
     * @return String сезон
     * {@code String season = getSeason_switch(11);} // Autumn
     * @throws IllegalArgumentException если month меньше 1 или month больше 12
     */
    public static String getSeason_switch(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("The month number is entered incorrectly! The month number must be between 1 and 12.");
        switch (month) {
            case 12:
            case 1:
            case 2:
                return "Winter";
            case 3:
            case 4:
            case 5:
                return "Spring";
            case 6:
            case 7:
            case 8:
                return "Summer";
            default:
                return "Autumn";
        }
    }

    /**
     * Определяет время года по номеру месяца
     * Зима: 12,1,2; Весна: 3,4,5; Лето: 6,7,8; Осень: 9,10,11
     *
     * @param month номер месяца
     * @return String сезон
     * {@code String season = getSeason_switch_exp(11);} // Autumn
     * @throws IllegalArgumentException если month меньше 1 или month больше 12
     */
    public static String getSeason_switch_exp(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("The month number is entered incorrectly! The month number must be between 1 and 12.");
        return switch (month) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            default -> "Autumn";
        };
    }

    /**
     * Рассчитывает итоговую цену со скидкой
     * Скидки: 0% до 1000, 5% 1000-5000, 10% 5000+
     *
     * @param price цена
     * @return double цена со скидкой
     * {@code double discountedPrice = calculateDiscount_if(1000.00);} // 950.00
     * @throws IllegalArgumentException если цена меньше нуля
     */
    public static double calculateDiscount_if(double price) {
        if (price < 0.00) throw new IllegalArgumentException("The price of a product cannot be less than zero.");
        if (price >= 5000.00) return BigDecimal.valueOf(0.90 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
        if (price >= 1000.00) return BigDecimal.valueOf(0.95 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Рассчитывает итоговую цену со скидкой
     * Скидки: 0% до 1000, 5% 1000-5000, 10% 5000+
     *
     * @param price цена
     * @return double цена со скидкой
     * {@code double discountedPrice = calculateDiscount_switch(1000.00);} // 950.00
     * @throws IllegalArgumentException если цена меньше нуля
     */
    public static double calculateDiscount_switch(double price) {
        if (price < 0.00) throw new IllegalArgumentException("The price of a product cannot be less than zero.");

        String category = "price < 1000.00";
        if (price >= 5000.00) category = "price >= 5000.00";
        else if (price >= 1000.00) category = "price >= 1000.00";

        switch (category) {
            case "price >= 5000.00":
                return BigDecimal.valueOf(0.90 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
            case "price >= 1000.00":
                return BigDecimal.valueOf(0.95 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
            default:
                return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
    }

    /**
     * Рассчитывает итоговую цену со скидкой
     * Скидки: 0% до 1000, 5% 1000-5000, 10% 5000+
     *
     * @param price цена
     * @return double цена со скидкой
     * {@code double discountedPrice = calculateDiscount_switch_exp(1000.00);} // 950.00
     * @throws IllegalArgumentException если цена меньше нуля
     */
    public static double calculateDiscount_switch_exp(double price) {
        if (price < 0.00) throw new IllegalArgumentException("The price of a product cannot be less than zero.");

        String category = "price < 1000.00";
        if (price >= 5000.00) category = "price >= 5000.00";
        else if (price >= 1000.00) category = "price >= 1000.00";

        return switch (category) {
            case "price >= 5000.00" -> BigDecimal.valueOf(0.90 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
            case "price >= 1000.00" -> BigDecimal.valueOf(0.95 * price).setScale(2, RoundingMode.HALF_UP).doubleValue();
            default -> BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
        };
    }

    /**
     * Рассчитывает итоговую цену с учетом скидки и бонусной карты
     *
     * @param price        исходная цена
     * @param hasBonusCard есть ли бонусная карта
     * @return double итоговая цена со всеми скидками
     * {@code double discountedPrice = calculateDiscountWithCard(1000.00, true);} // 931.00
     * @throws IllegalArgumentException если цена меньше 1.00
     */
    public static double calculateDiscountWithCard(double price, boolean hasBonusCard) {
        double minPrice = 1.00;
        if (price < minPrice) throw new IllegalArgumentException("The price of a product cannot be less than 1.00.");

        double discount = (price >= 5000.00) ? 0.90 :
                (price >= 1000.00) ? 0.95 : 1;

        double discountedPrice = hasBonusCard ? price * discount * 0.98 : price * discount;

        return Math.max(BigDecimal.valueOf(discountedPrice).setScale(2, RoundingMode.HALF_UP).doubleValue(), minPrice);
    }


    /**
     * Проверяет, можно ли построить треугольник с заданными сторонами и определяет его тип (равносторонний, равнобедренный, разносторонний)
     * @param a сторона A
     * @param b сторона B
     * @param c сторона C
     * @return String тип треугольника или сообщение "Треугольник невозможен"
     * {@code String triangleType = triangleType(5, 5, 5);} // Equilateral
     * {@code String triangleType = triangleType(6, 6, 5);} // Isosceles
     * {@code String triangleType = triangleType(3, 4, 5);} // Scalene: all sides are different
     * @throws IllegalArgumentException если одна из сторон треугольника меньше нуля
     * @throws IllegalArgumentException если сумма любых двух сторон меньше третьей
     */
    public static String triangleType(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("The length of a side of a triangle must be greater than zero.");
        if (a + b < c || b + c < a || a + c < b) throw new IllegalArgumentException("A triangle is possible if the sum of any two sides is greater than the third.");
        return (a == b && b == c) ? "Equilateral" :
                (a == b || b == c || a == c) ? "Isosceles" : "Scalene: all sides are different";
    }
}
