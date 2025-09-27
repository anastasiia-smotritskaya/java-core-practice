package com.github.anastasiiasmotritskaya.javacore;

/**
 * Практика с классами String и StringBuilder
 * Примеры: методы класса String, методы класса StringBuilder
 * * @author Анастасия Смотрицкая
 * * @version 1.0
 */
public class StringPractice {
    /**
     * Определяет, является ли строка палиндромом.
     *
     * @param original строка для проверки
     * @return true если строка палиндром, false если строка не палиндром
     * @throws IllegalArgumentException если строка равна null
     * @code boolean result = isPalindrome("Madam"); // true
     * boolean result = isPalindrome("Adam"); // false
     * @see java.lang.StringBuilder
     */
    public static boolean isPalindrome(String original) {

        if (original == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        } else {
            // регулярное выражение \\s+ удаляет все пробелы (не только одинарные)
            String cleaned = original.replaceAll("\\s+", "").toLowerCase();
            return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
        }
    }
}
