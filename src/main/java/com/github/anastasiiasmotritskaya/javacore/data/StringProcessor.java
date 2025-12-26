package com.github.anastasiiasmotritskaya.javacore.data;

import java.util.HashMap;
import java.util.Map;

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
     *                                  {@code boolean result = isPalindrome("Madam")}; // true
     *                                  {@code boolean result = isPalindrome("Adam");} // false
     * @see java.lang.StringBuilder
     */
    public static boolean isPalindrome(String original) {

        if (original == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        // регулярное выражение \\s+ удаляет все пробелы (не только одинарные)
        String cleaned = original.replaceAll("\\s+", "").toLowerCase();
        return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
    }

    /**
     * Определяет, является ли строка палиндромом (неэффективно, для демонстрации)
     * Не использовать для больших строк!
     *
     * @param original строка для проверки
     * @return true если строка палиндром, false если строка не палиндром
     * @throws IllegalArgumentException если строка равна null
     *                                  {@code boolean result = isPalindrome_char("Madam")}; // true
     *                                  {@code boolean result = isPalindrome_char("Adam");} // false
     */
    public static boolean isPalindrome_char(String original) {

        if (original == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        String cleaned = original.replaceAll("\\s+", "").toLowerCase();

        String result = "";
        for (int i = cleaned.length() - 1; i > -1; i--) {
            result += cleaned.charAt(i);
        }
        return result.equals(cleaned);
    }

    /**
     * Переворачивает порядок символов в строке
     *
     * @param str строка для переворота (не может быть null)
     * @return перевернутую строку
     * @throws IllegalArgumentException если str равен null
     *                                  {@code String result = reverseString("Test")}; // tseT
     * @see java.lang.StringBuilder
     */
    public static String reverseString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Переворачивает строку с помощью конкатенации (неэффективно, для демонстрации)
     * Не использовать для больших строк!
     *
     * @param str строка для переворота (не может быть null)
     * @return перевернутую строку
     * @throws IllegalArgumentException если str равен null
     *                                  {@code String result = reverseString_char("Test")}; // tseT
     */
    public static String reverseString_char(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        String result = "";
        for (int i = str.length() - 1; i > -1; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    /**
     * Считает количество гласных в строковой переменной
     *
     * @param str строка для подсчета гласных (не может быть null)
     * @return количество гласных
     * {@code int result = countVowels("camel);} // 2
     * {@code int result = countVowels("");} // 0
     * {@code int result = countVowels(null);} // 0
     */
    public static int countVowels(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return str.replaceAll("[^aeiouAEIOUауоыиэяюёеАУОЫИЭЯЮЁЕ]", "").length();
    }

    /**
     * Удаляет все пробелы из строки
     *
     * @param str строка для обработки
     * @return строку без пробелов
     * {@code String result = removeSpaces("c a m e l");} // "camel"
     */
    public static String removeSpaces(String str) {
        if (str == null) return null;
        return str.replaceAll("\\s", "");
    }

    /**
     * Приводит первую букву каждого слова к верхнему регистру, остальные - к нижнему
     *
     * @param str строка для обработки
     * @return строку с заглавными первыми буквами каждого слова
     * {@code String result = capitalizeWords("hello world");} // "Hello World"
     */
    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) return str;

        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

    /**
     * Подсчитывает частоту каждого символа в строке
     *
     * @param str исходная строка
     * @return Map (ключ - Character - символ, значение - Integer - количество)
     * {@code Map<Character, Integer> charCountMap = countCharacters("hello");} // {e=1, h=1, l=2, o=1}
     * @throws IllegalArgumentException если строка пустая или равно null
     */
    public static Map<Character, Integer> countCharacters(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        if (str.isEmpty()) {
            return new HashMap<>();
        }

        Map<Character, Integer> charCountMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            int currentCount = charCountMap.getOrDefault(str.charAt(i), 0);
            charCountMap.put(str.charAt(i), ++currentCount);
        }

        return charCountMap;
    }

    /**
     * Находит самое длинное слово в строке
     * Слово - последовательность символов без пробелов
     *
     * @param text исходный текст
     * @return самое длинное слово
     * {@code = String word = findLongestWord("Java is a programming language");} // programming
     * @throws IllegalArgumentException если строка null, пустая или состоит только из пробелов
     */
    public static String findLongestWord(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        String trimmed = text.trim();

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty.");
        }

        String[] words = trimmed.split("\\s+");

        String longestWord = words[0];
        int longestWordLength = words[0].length();

        for (String word : words) {
            if (word.length() > longestWordLength) {
                longestWordLength = word.length();
                longestWord = word;
            }
        }
        return longestWord;
    }
}