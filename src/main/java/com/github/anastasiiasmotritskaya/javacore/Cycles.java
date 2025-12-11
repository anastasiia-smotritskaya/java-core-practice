package com.github.anastasiiasmotritskaya.javacore;

/**
 * Работа с циклами
 * Методы: вычисление суммы чисел, поиск простых чисел, таблица умножения
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */

public class Cycles {

    /**
     * Конструктор по умолчанию для javadoc
     */
    private Cycles() {
    }

    /**
     * Вычисляет сумму всех чисел от 1 до n включительно
     *
     * @param n положительное целое число
     * @return сумма чисел от 1 до n
     * {@code int sum = sumOfNumbers_for(100);} // 5050
     * @throws IllegalArgumentException если n меньше или равно нулю
     */
    public static int sumOfNumbers_for(int n) {
        if (n <= 0) throw new IllegalArgumentException("The number of numbers must not be less than or equal to zero.");
        int sum = 0;
        for (int i = 1; i < n + 1; i++) {
            sum = Math.addExact(sum, i);
        }
        return sum;
    }

    /**
     * Вычисляет сумму всех чисел от 1 до n включительно
     *
     * @param n положительное целое число
     * @return сумма чисел от 1 до n
     * {@code int sum = sumOfNumbers_while(100);} // 5050
     * @throws IllegalArgumentException если n меньше или равно нулю
     */
    public static int sumOfNumbers_while(int n) {
        if (n <= 0) throw new IllegalArgumentException("The number of numbers must not be less than or equal to zero.");

        int sum = 0;
        int term = 1;

        while (term <= n) {
            sum = Math.addExact(sum, term);
            term++;
        }
        return sum;
    }

    /**
     * Вычисляет сумму всех чисел от 1 до n включительно
     *
     * @param n положительное целое число
     * @return сумма чисел от 1 до n
     * {@code int sum = sumOfNumbers_do(100);} // 5050
     * @throws IllegalArgumentException если n меньше или равно нулю
     */
    public static int sumOfNumbers_do(int n) {
        if (n <= 0) throw new IllegalArgumentException("The number of numbers must not be less than or equal to zero.");

        int sum = 0;
        int term = 1;

         do {
            sum = Math.addExact(sum, term);
            term++;
        }
        while (term <= n);

        return sum;
    }
}
