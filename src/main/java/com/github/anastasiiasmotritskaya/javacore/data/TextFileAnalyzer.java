package com.github.anastasiiasmotritskaya.javacore.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Работа с системами ввода-вывода
 * Методы:
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class TextFileAnalyzer {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private TextFileAnalyzer() {
    }

    /**
     * Считает количество строк в файле.
     * Каждая строка, включая пустые, считается, если после неё есть перевод строки.
     * Пустая строка в конце файла (без перевода строки) не считается отдельной строкой.
     *
     * @param filePath путь к файлу
     * @return int количество строк в файле
     * {@code int linesCount = countLines_br(data.txt);} || возвращает количество строк
     * @throws IOException              если файл не найден или произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если путь к файлу пустой или null
     * @see BufferedReader
     * @see FileReader
     */
    public static int countLines_br(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        }
    }

    /**
     * Считает количество строк в файле, используя Files.readAllLines().
     * Пустые строки внутри файла учитываются.
     * Поведение для последней пустой строки без перевода строки зависит от реализации JVM.
     * Для более предсказуемого поведения с пустыми строками используйте countLines_br().
     *
     * @param filePath путь к файлу
     * @return int количество строк в файле
     * {@code int linesCount = countLines_files(data.txt);} || возвращает количество строк
     * @throws IOException              если файл не найден или произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если путь к файлу пустой или null
     * @see Path
     * @see Files
     */
    public static int countLines_files(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        return lines.size();
    }

    /**
     * Находит самое длинное слово в файле, обрабатывая файл построчно для эффективности.
     * Слово определяется как последовательность букв, может содержать дефисы и апострофы внутри.
     * Регистр не учитывается при сравнении длины, но возвращается слово в оригинальном виде.
     *
     * <p>Пример: "mother-in-law", "O'Connor", "hello" считаются словами.
     *
     * @param filePath путь к файлу
     * @return самое длинное слово или пустую строку, если слов нет
     * @throws IOException              если файл не найден или ошибка ввода-вывода
     * @throws IllegalArgumentException если путь null или пустой
     * @see BufferedReader
     * @see FileReader
     */
    public static String findLongestWord_br(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        String longestWord = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("[^\\p{L}'-]+");
                for (String word : words) {
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
        }
        return longestWord;
    }

    /**
     * Находит самое длинное слово в файле, обрабатывая файл построчно для эффективности.
     * Слово определяется как последовательность букв, может содержать дефисы и апострофы внутри.
     * Регистр не учитывается при сравнении длины, но возвращается слово в оригинальном виде.
     *
     * <p>Пример: "mother-in-law", "O'Connor", "hello" считаются словами.
     *
     * @param filePath путь к файлу
     * @return самое длинное слово или пустую строку, если слов нет
     * @throws IOException              если файл не найден или ошибка ввода-вывода
     * @throws IllegalArgumentException если путь null или пустой
     * @see Files
     */
    public static String findLongestWord_files(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        Path path = Paths.get(filePath);
        String longestWord = "";

        for (String line : Files.readAllLines(path)) {
            String[] words = line.split("[^\\p{L}'-]+");
            for (String word : words) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }
}