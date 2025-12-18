package com.github.anastasiiasmotritskaya.javacore.basicsyntax;

/**
 * Анализатор чисел
 * Методы: проверка, простое ли число, поиск наибольшего числа из трех, сумма цифр числа, проверка на положительность,
 * проверка на положительность и четность
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class NumberAnalyzer {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private NumberAnalyzer() {
    }

    /**
     * Проверяет, является ли число простым
     *
     * @param number число, которое необходимо проверить
     * @return true если число простое, false если число не простое
     * {@code boolean result = isPrime(3);}  // true
     * {@code boolean result = isPrime(6);} // false
     * @throws IllegalArgumentException если number ≤ 1
     */
    public static boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalArgumentException("The number 1 is neither prime nor composite," +
                    " and numbers less than 1 are not prime numbers.");
        } else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Находит максимальное среди трех чисел
     *
     * @param a первое число для сравнения
     * @param b второе число для сравнения
     * @param c третье число для сравнения
     * @return максимальное из трех чисел
     * {@code int result = findMax(1, 2, 3);} // 3
     */
    public static int findMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * Находит сумму цифр числа
     *
     * @param number число, для которого необходимо найти сумму цифр (может быть отрицательным)
     * @return сумму цифр
     * {@code int result = sumDigits(123);} // 6
     */
    public static int sumDigits(int number) {
        int sum = 0;
        int num = Math.abs(number);
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }

    /**
     * Проверяет, является ли число положительным
     *
     * @param number число для анализа
     * @return true если число положительное, false если число 0 или отрицательное
     * {@code boolean result = isPositive(10);} // true
     * {@code boolean result = isPositive(-10);} // false
     */
    public static boolean isPositive(int number) {
        return (number > 0);
    }

    /**
     * Форматирует строку с описанием четности и знака числа
     *
     * @param number число для анализа
     * @return строку вида "The number X is [even/odd] and [positive/not positive]"
     * {@code String result = formatNumber(5);} // "The number 5 is odd and positive"
     * {@code String result = formatNumber(-4);} // "The number -4 is even and not positive"
     */
    public static String formatNumber(int number) {
        String evenness = number % 2 == 0 ? "even" : "odd";
        String positive = number > 0 ? "positive" : "not positive";
        return "The number " + number + " is " + evenness + " and " + positive;
    }
}
