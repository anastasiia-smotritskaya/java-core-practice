package com.github.anastasiiasmotritskaya.javacore;

/**
 * Обработчик строк с различными операциями
 * Примеры: методы класса String, методы класса StringBuilder
 *
 * @author Анастасия Смотрицкая
 * @version 2.0
 */
public class StringProcessor {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private StringProcessor() {

    }

    /**
     * Определяет, является ли строка палиндромом.
     *
     * @param original строка для проверки
     * @return true если строка палиндром, false если строка не палиндром
     * @throws IllegalArgumentException если строка равна null
     * {@code boolean result = isPalindrome("Madam")}; // true
     * {@code boolean result = isPalindrome("Adam");} // false
     * @see java.lang.StringBuilder
     */
    public static boolean isPalindrome(String original) {

        if (original == null) {
            throw new IllegalArgumentException("Illegal argument: empty variable, String argument required");
        } else {
            // регулярное выражение \\s+ удаляет все пробелы (не только одинарные)
            String cleaned = original.replaceAll("\\s+", "").toLowerCase();
            return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
        }
    }

    /**
     * Переворачивает порядок символов в строке
     *
     * @param str строка для переворота (не может быть null)
     * @return перевернутую строку
     * @throws IllegalArgumentException если str равен null
     * {@code String result = reverseString("Test")}; // tseT
     * @see java.lang.StringBuilder
     */
    public static String reverseString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal argument: empty variable, String argument required");
        }
        return new StringBuilder(str).reverse().toString();
    }
}
