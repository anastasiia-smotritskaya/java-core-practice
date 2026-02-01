package com.github.anastasiiasmotritskaya.javacore.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с коллекциями строк
 * Методы: фильтрация строк бьльше определенной длины, преобразование всех строк в верхний регистр,
 * объединение строк через разделитель
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
     * {@code List result = filterLongStrings_for("one", "two", "three", 4);} // {"three"}
     * @throws IllegalArgumentException если strings null или empty
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
     * {@code List result = filterLongStrings_lambda("one", "two", "three", 4);} // {"three"}
     * @throws IllegalArgumentException если strings null или empty
     */
    public static List<String> filterLongStrings_lambda(List<String> strings, int minLength) {
        validateStrings(strings);
        return strings.stream().map(String::trim).filter(trim -> trim.length() > minLength).collect(Collectors.toList());
    }

    /**
     * Фильтрует все строки больше определенной длины (используется method reference и доп. лямбда-выражение)
     *
     * @param strings   список строк
     * @param minLength строки данной длины и меньше не должны попадать в итоговый список
     * @return список строк, длина которых больше minLength
     * {@code List result = filterLongStrings_mr("one", "two", "three", 4);} // {"three"}
     * @throws IllegalArgumentException если strings null или empty
     */
    public static List<String> filterLongStrings_mr(List<String> strings, int minLength) {
        validateStrings(strings);
        return strings.stream()
                .map(String::trim)
                .filter(trim -> isLengthBigger(trim, minLength))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, не является ли список строк null или empty
     * Вспомогательный метод
     */
    private static void validateStrings(List<String> strings) {
        if (strings == null) {
            throw new IllegalArgumentException("List of strings must not be null.");
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
