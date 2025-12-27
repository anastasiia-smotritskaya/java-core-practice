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
     * {@code String word = findLongestWord("Java is a programming language");} // programming
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

    /**
     * Форматирует номер телефона
     *
     * @param phoneNumber строка с цифрами
     * @return отформатированный номер: +7 (XXX) XXX-XX-XX
     * {@code String phoneNumber = formatPhoneNumber_replace("89123456789");} // +7 (912) 345-67-89
     * @throws IllegalArgumentException если номер не из 11 цифр
     */
    public static String formatPhoneNumber_replace(String phoneNumber) {
        final int NUMBER_OF_DIGITS = 11;

        if (phoneNumber == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        String cleaned = phoneNumber.replaceAll("\\D", "");

        if (cleaned.length() != NUMBER_OF_DIGITS) {
            throw new IllegalArgumentException("The phone number must be 11 digits long.");
        }

        if (!(cleaned.charAt(0) == '7' || cleaned.charAt(0) == '8')) {
            throw new IllegalArgumentException("Please check that your phone number is entered correctly. Russian numbers must begin with 7 or 8.");
        }

        StringBuilder sb = new StringBuilder("+7 (XXX) XXX-XX-XX");
        sb.replace(4, 7, cleaned.substring(1, 4));
        sb.replace(9, 12, cleaned.substring(4, 7));
        sb.replace(13, 15, cleaned.substring(7, 9));
        sb.replace(16, 18, cleaned.substring(9, 11));

        return sb.toString();
    }

    /**
     * Форматирует номер телефона
     *
     * @param phoneNumber строка с цифрами
     * @return отформатированный номер: +7 (XXX) XXX-XX-XX
     * {@code String phoneNumber = formatPhoneNumber_append("89123456789");} // +7 (912) 345-67-89
     * @throws IllegalArgumentException если номер не из 11 цифр
     */
    public static String formatPhoneNumber_append(String phoneNumber) {
        final int NUMBER_OF_DIGITS = 11;

        if (phoneNumber == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        String cleaned = phoneNumber.replaceAll("\\D", "");

        if (cleaned.length() != NUMBER_OF_DIGITS) {
            throw new IllegalArgumentException("The phone number must be 11 digits long.");
        }

        if (!(cleaned.charAt(0) == '7' || cleaned.charAt(0) == '8')) {
            throw new IllegalArgumentException("Please check that your phone number is entered correctly. Russian numbers must begin with 7 or 8.");
        }

        StringBuilder sb = new StringBuilder("+7 (")
                .append(cleaned, 1, 4)
                .append(") ")
                .append(cleaned, 4, 7)
                .append("-")
                .append(cleaned, 7, 9)
                .append("-")
                .append(cleaned, 9, 11);

        return sb.toString();
    }

    /**
     * Проверяет валидность email адреса
     * Простая проверка: содержит @, есть домен, нет пробелов
     *
     * @param email строка для проверки
     * @return true если email валиден
     * {@code boolean isValid = isValidEmail("test@example.com");} // true
     * {@code boolean isValid = isValidEmail("invalid.email");} // false
     * @throws IllegalArgumentException если email не соответствует шаблону
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;

        String trimmed = email.trim();
        if (trimmed.isEmpty() || trimmed.contains(" ")) return false;

        char[] chars = trimmed.toCharArray();
        int atSignCount = 0;

        for (char c : chars) {
            if (c == '@') atSignCount++;
        }

        if (atSignCount != 1) return false;

        final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}$";

        return trimmed.matches(EMAIL_PATTERN);
    }
}