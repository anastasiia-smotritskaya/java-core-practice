package com.github.anastasiiasmotritskaya.javacore.fp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс для работы с коллекциями строк
 * Методы: фильтрация строк больше определенной длины, преобразование всех строк в верхний регистр,
 * объединение строк через разделитель и пр.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class StringUtils {
    /**
     * Фильтрует все строки больше определенной длины (используется цикл for-loop)
     *
     * @param strings   список строк
     * @param minLength строки данной длины и меньше не должны попадать в итоговый список
     * @return список строк, длина которых больше minLength
     * {@code List result = filterLongStrings_for("one", "two", "three");} // {"three"}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> filterLongStrings_for(List<String> strings, int minLength) {
        validateStrings(strings);
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            String trimmed = s.trim();
            if (trimmed.length() > minLength) {
                result.add(trimmed);
            }
        }
        return result;
    }

    /**
     * Фильтрует все строки больше определенной длины (используется лямбда-выражение)
     *
     * @param strings   список строк
     * @param minLength строки данной длины и меньше не должны попадать в итоговый список
     * @return список строк, длина которых больше minLength
     * {@code List result = filterLongStrings_lambda("one", "two", "three");} // {"three"}
     * @throws IllegalArgumentException если strings null
     */
    public static List<String> filterLongStrings_lambda(List<String> strings, int minLength) {
        validateStrings(strings);
        return strings.stream()
                .map(String::trim)
                .filter(trim -> trim.length() > minLength)
                .collect(Collectors.toList());
    }

    /**
     * Фильтрует все строки больше определенной длины (используется method reference и доп. лямбда-выражение)
     *
     * @param strings   список строк
     * @param minLength строки данной длины и меньше не должны попадать в итоговый список
     * @return список строк, длина которых больше minLength
     * {@code List result = filterLongStrings_mr("one", "two", "three");} // {"three"}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> filterLongStrings_mr(List<String> strings, int minLength) {
        validateStrings(strings);
        return strings.stream()
                .map(String::trim)
                .filter(trim -> isLengthBigger(trim, minLength))
                .collect(Collectors.toList());
    }

    /**
     * Приводит все строки в списке к верхнему регистру (используется цикл for)
     *
     * @param strings список строк
     * @return все строки из списка, приведенные к верхнему регистру
     * {@code toUpperCaseTrimmed_for(List.of("one", "two", "three"))} вернёт {@code {"ONE", "TWO", "THREE"}}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> toUpperCaseTrimmed_for(List<String> strings) {
        validateStrings(strings);
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            String trimmed = s.trim();
            result.add(trimmed.toUpperCase());
        }
        return result;
    }

    /**
     * Приводит все строки в списке к верхнему регистру (используется stream api)
     *
     * @param strings список строк
     * @return все строки из списка, приведенные к верхнему регистру
     * {@code toUpperCaseTrimmed_stream(List.of("one", "two", "three"))} вернёт {@code {"ONE", "TWO", "THREE"}}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> toUpperCaseTrimmed_stream(List<String> strings) {
        validateStrings(strings);
        return strings.stream()
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /**
     * Объединяет строки через разделитель (используется цикл for)
     *
     * @param strings   список строк
     * @param delimiter разделитель
     * @return String строку, составленную из всех строк в списке, разделенных разделителем
     * {@code joinStrings_for(List.of("one", "two", "three"), "@")} вернёт {@code "one@two@three"}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static String joinStrings_for(List<String> strings, String delimiter) {
        validateStrings(strings);
        StringBuilder joinedString = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            String trimmed = strings.get(i).trim();
            if (i < strings.size() - 1) {
                joinedString.append(trimmed).append(delimiter);
            } else if (i == strings.size() - 1) {
                joinedString.append(trimmed);
            }
        }
        return joinedString.toString();
    }

    /**
     * Объединяет строки через разделитель (используется stream api)
     *
     * @param strings   список строк
     * @param delimiter разделитель
     * @return String строку, составленную из всех строк в списке, разделенных разделителем
     * {@code joinStrings_stream(List.of("one", "two", "three"), "@")} вернёт {@code "one@two@three"}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static String joinStrings_stream(List<String> strings, String delimiter) {
        validateStrings(strings);
        return strings.stream()
                .map(String::trim)
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Сортирует строки по длине (используется цикл for)
     *
     * @param strings список строк для сортировке по длине
     * @return список строк, отсортированных по длине
     * {@code sortByLength_for(List.of("cat", "hamster", "dog", "guinea pig"))}
     * вернёт {@code List.of("cat", "dog", "hamster", "guinea pig")}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> sortByLength_for(List<String> strings) {
        validateStrings(strings);
        List<String> result = new ArrayList<>(strings);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                if (result.get(j).length() > result.get(j + 1).length()) {
                    String temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    /**
     * Сортирует строки по длине (используется stream api)
     *
     * @param strings список строк для сортировке по длине
     * @return список строк, отсортированных по длине
     * {@code sortByLength_stream(List.of("cat", "hamster", "dog", "guinea pig")}
     * вернёт {@code List.of("cat", "dog", "hamster", "guinea pig")}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static List<String> sortByLength_stream(List<String> strings) {
        validateStrings(strings);
        return strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
    }

    /**
     * Проверяет, не является ли список строк или отдельная строка в списке null
     * Вспомогательный метод
     *
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    private static void validateStrings(List<String> strings) {
        if (strings == null) {
            throw new IllegalArgumentException("List of strings should not be null.");
        }
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i) == null) {
                throw new IllegalArgumentException(String.format("Element at index %d must not be null", i));
            }
        }
    }

    /**
     * Проверяет, является ли строка больше заданной длины
     * Вспомогательный метод для filterLongStrings_mr
     *
     * @return true, если длина строки больше inLength
     * false, если длина строки меньше или равна inLength
     */
    private static boolean isLengthBigger(String s, int minLength) {
        return s.length() > minLength;
    }
}
