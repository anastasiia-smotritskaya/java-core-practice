package com.github.anastasiiasmotritskaya.javacore.fp;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


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
     * Проверяет, есть ли строка, содержащая подстроку (используется цикл for)
     *
     * @param strings   список строк для поиска подстроки
     * @param substring сподстрока для поиска в списке строк
     * @return boolean true - если подстрока есть в списке
     * boolean false - если подстроки нет в списке
     * {@code containsSubstring_for(List.of("cat", "hamster", "dog", "guinea pig")}  //true
     * @throws IllegalArgumentException если список строк, отдельная строка или подстрока null
     */
    public static boolean containsSubstring_for(List<String> strings, String substring) {
        validateStrings(strings);

        if (substring == null || substring.isEmpty()) {
            throw new IllegalArgumentException("Substring should not be null or empty.");
        }

        String substringLowerCase = substring.toLowerCase();

        for (String s : strings) {
            String sLowerCase = s.toLowerCase();
            if (sLowerCase.contains(substringLowerCase)) return true;
        }

        return false;
    }

    /**
     * Проверяет, есть ли строка, содержащая подстроку (используется stream api)
     *
     * @param strings   список строк для поиска подстроки
     * @param substring сподстрока для поиска в списке строк
     * @return boolean true - если подстрока есть в списке
     * boolean false - если подстроки нет в списке
     * {@code containsSubstring_stream(List.of("cat", "hamster", "dog", "guinea pig")}  //true
     * @throws IllegalArgumentException если список строк, отдельная строка или подстрока null
     */
    public static boolean containsSubstring_stream(List<String> strings, String substring) {
        validateStrings(strings);

        if (substring == null || substring.isEmpty()) {
            throw new IllegalArgumentException("Substring should not be null or empty.");
        }

        return strings.stream()
                .map(String::toLowerCase)
                .anyMatch(s -> s.contains(substring.toLowerCase()));
    }

    /**
     * Группирует строки из списка по первой букве (используется цикл for)
     *
     * @param strings список строк для группирования по первой букве
     * @return Map, состоящий из ключа (Character) - буквы, с которой начинается строка
     * и значения (List) - списка строк, которые начинаются с этой буквы
     * {@code groupByFirstLetter_for(List.of("cat", "dog", "cow")}
     * вернёт {@code Map.of('c', List.of("cat", "cow), 'd', List.of("dog"))}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static Map<Character, List<String>> groupByFirstLetter_for(List<String> strings) {
        validateStrings(strings);

        Map<Character, List<String>> result = new HashMap<>();

        for (String s : strings) {
            if (s.isEmpty()) {
                continue;
            }
            char firstLetter = s.toLowerCase().charAt(0);

            if (!result.containsKey(firstLetter)) {
                result.put(firstLetter, new ArrayList<>());

            }
            result.get(firstLetter).add(s);
        }

        return result;
    }

    /**
     * Группирует строки из списка по первой букве (используется stream api)
     *
     * @param strings список строк для группирования по первой букве
     * @return Map, состоящий из ключа (Character) - буквы, с которой начинается строка
     * и значения (List) - списка строк, которые начинаются с этой буквы
     * {@code groupByFirstLetter_stream(List.of("cat", "dog", "cow")}
     * вернёт {@code Map.of('c', List.of("cat", "cow), 'd', List.of("dog"))}
     * @throws IllegalArgumentException если список строк или отдельная строка null
     */
    public static Map<Character, List<String>> groupByFirstLetter_stream(List<String> strings) {
        validateStrings(strings);

        return strings.stream()
                .filter(s -> !s.isEmpty())
                .collect(groupingBy(s -> s.toLowerCase().charAt(0)));
    }

    /**
     * Считает частоту слов в списке (используется цикл for)
     *
     * @param words список слов для подсчета
     * @return Map, состоящий из ключа (String) - слова и значения (Long) - количества повторений этого слова в списке
     * {@code countWordFrequency_for(List.of("cat", "dog", "cow")}
     * вернёт {@code Map.of("cat", 1, "dog", 1, "cow", 1,)}
     * @throws IllegalArgumentException если список слов или отдельное слово null
     */
    public static Map<String, Long> countWordFrequency_for(List<String> words) {
        validateStrings(words);

        Map<String, Long> result = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            String normalizedWord = word.trim().toLowerCase();

            long count = result.getOrDefault(normalizedWord, 0L) + 1;

            result.put(normalizedWord, count);
        }

        return result;
    }

    /**
     * Считает частоту слов в списке (используется stream api)
     *
     * @param words список слов для подсчета
     * @return Map, состоящий из ключа (String) - слова и значения (Long) - количества повторений этого слова в списке
     * {@code countWordFrequency_stream(List.of("cat", "dog", "cow")}
     * вернёт {@code Map.of("cat", 1, "dog", 1, "cow", 1,)}
     * @throws IllegalArgumentException если список слов или отдельное слово null
     */
    public static Map<String, Long> countWordFrequency_stream(List<String> words) {
        validateStrings(words);

        return words.stream()
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .collect(groupingBy(String::toLowerCase, Collectors.counting()));
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
