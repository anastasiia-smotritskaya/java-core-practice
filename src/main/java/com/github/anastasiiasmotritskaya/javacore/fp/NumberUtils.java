package com.github.anastasiiasmotritskaya.javacore.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с коллекциями чисел
 * Методы: фильтрация нечетных чисел, умножение всех чисел на 2, сумма всех чисел
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class NumberUtils {
    /**
     * Фильтрует четные числа (используется цикл for-loop)
     *
     * @param numbers список чисел для фильтрации нечетных
     * @return список четных чисел, если они есть и пустой список, если четных чисел нет
     * {@code List result = filterEvenNumbers_for(1, 2, 3, 4, 5);} // {2, 4}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> filterEvenNumbers_for(List<Integer> numbers) {
        validateNumbers(numbers);

        List<Integer> result = new ArrayList<>();

        for (Integer number : numbers) {
            if (number % 2 == 0) {
                result.add(number);
            }
        }
        return result;
    }

    /**
     * Фильтрует четные числа (используется лямбда-выражение)
     *
     * @param numbers список чисел для фильтрации нечетных
     * @return список четных чисел, если они есть и пустой список, если четных чисел нет
     * {@code List result = filterEvenNumbers_lambda(1, 2, 3, 4, 5);} // {2, 4}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> filterEvenNumbers_lambda(List<Integer> numbers) {
        validateNumbers(numbers);

        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    /**
     * Фильтрует четные числа (используется method referenced)
     *
     * @param numbers список чисел для фильтрации нечетных
     * @return список четных чисел, если они есть и пустой список, если четных чисел нет
     * {@code List result = filterEvenNumbers_lambda(1, 2, 3, 4, 5);} // {2, 4}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> filterEvenNumbers_mr(List<Integer> numbers) {
        validateNumbers(numbers);

        return numbers.stream()
                .filter(NumberUtils::isEven)
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, является ли число четным
     * Вспомогательный метод для filterEvenNumbers_mr
     */
    private static boolean isEven(Integer number) {
        return number % 2 == 0;
    }

    /**
     * Увеличивает каждое число в списке в 2 раза (используется цикл for)
     *
     * @param numbers список чисел для изменения
     * @return список чисел, в котором каждое число исходного списка увеличено в 2 раза
     * {@code List result = doubleNumbers_for(1, 2, 3, 4, 5);} // {2, 4, 6, 8, 10}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> doubleNumbers_for(List<Integer> numbers) {
        validateNumbers(numbers);
        List<Integer> result = new ArrayList<>(numbers.size());
        for (Integer number : numbers) {
            result.add(number * 2);
        }
        return result;
    }

    /**
     * Увеличивает каждое число в списке в 2 раза (используется лямбда-выражение)
     *
     * @param numbers список чисел для изменения
     * @return список чисел, в котором каждое число исходного списка увеличено в 2 раза
     * {@code List result = doubleNumbers_lambda(1, 2, 3, 4, 5);} // {2, 4, 6, 8, 10}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> doubleNumbers_lambda(List<Integer> numbers) {
        validateNumbers(numbers);
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    /**
     * Увеличивает каждое число в списке в 2 раза (используется method references)
     *
     * @param numbers список чисел для изменения
     * @return список чисел, в котором каждое число исходного списка увеличено в 2 раза
     * {@code List result = doubleNumbers_mr(1, 2, 3, 4, 5);} // {2, 4, 6, 8, 10}
     * @throws IllegalArgumentException если numbers null
     */
    public static List<Integer> doubleNumbers_mr(List<Integer> numbers) {
        validateNumbers(numbers);
        return numbers.stream()
                .map(NumberUtils::doubleNumber)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает сумму чисел из списка (используется цикл for)
     *
     * @param numbers список чисел для суммирования
     * @return сумму чисел из списка
     * {@code sumNumbers_for(List.of(1, 2, 3))} вернёт {@code 6}
     * @throws IllegalArgumentException если numbers null
     */
    public static int sumNumbers_for(List<Integer> numbers) {
        validateNumbers(numbers);
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    /**
     * Возвращает сумму чисел из списка (используется лямбда-выражение)
     *
     * @param numbers список чисел для суммирования
     * @return сумму чисел из списка
     * {@code sumNumbers_lambda(List.of(1, 2, 3))} вернёт {@code 6}
     * @throws IllegalArgumentException если numbers null
     */
    public static int sumNumbers_lambda(List<Integer> numbers) {
        validateNumbers(numbers);
        return numbers.stream().reduce(0, Integer::sum);
    }

    /**
     * Умножает число на 2
     * Вспомогательный метод для doubleNumbers_mr
     */
    private static Integer doubleNumber(Integer number) {
        return number * 2;
    }

    /**
     * Проверяет, не является ли список чисел null
     * Вспомогательный метод
     */
    private static void validateNumbers(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List of numbers must not be null.");
        }
    }
}
