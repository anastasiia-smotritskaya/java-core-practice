package com.github.anastasiiasmotritskaya.javacore.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
     * @throws IOException если файл не найден или произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если путь к файлу пустой или null
     * @see BufferedReader
     * @see FileReader
     */
    public static int countLines_br(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty. Enter the file path.");
        }

        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        }
    }
}