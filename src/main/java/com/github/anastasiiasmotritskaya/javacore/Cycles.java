package com.github.anastasiiasmotritskaya.javacore;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Находит все простые числа в диапазоне от start до end включительно
     * Простое число - натуральное число больше 1, которое делится только на 1 и на себя
     *
     * @param start начало диапазона
     * @param end   конец диапазона
     * @return список простых чисел в диапазоне
     * {@code ArrayList<Integer> primeNumbers = findPrimeNumbers(2, 10);} // {2, 3, 5, 7}
     * @throws IllegalArgumentException если start больше end или start меньше 2
     */
    public static List<Integer> findPrimeNumbers(int start, int end) {
        if (start < 2) throw new IllegalArgumentException("Start must be at least 2. Given: " + start + ".");
        if (start > end)
            throw new IllegalArgumentException("The start must be <= end. Given: start = " + start + ", end = " + end + ".");

        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        int limit = (int) Math.sqrt(number);
        for (int i = 3; i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Генерирует таблицу умножения размера n x n
     *
     * @param n размер таблицы (от 1 до 10 включительно)
     * @return двумерный массив таблицы умножения
     * {@code int[][] table = multiplicationTable(3);} // [[1,2,3],[2,4,6],[3,6,9]]
     * @throws IllegalArgumentException если n вне диапазона [1, 10]
     */
    public static int[][] multiplicationTable(int n) {
        if (n < 1 || n > 10) throw new IllegalArgumentException("The entered value must be between 1 and 10.");

        int[][] table = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
