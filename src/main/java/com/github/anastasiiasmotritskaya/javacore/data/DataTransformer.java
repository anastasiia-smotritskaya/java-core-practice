package com.github.anastasiiasmotritskaya.javacore.data;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Работа с системами ввода-вывода
 * Методы: запись строк, начинающихся на А/а из одного файла в другой
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class DataTransformer {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private DataTransformer() {
    }

    /**
     * Метод читает файл inputFilePath, выбирает только те строки,
     * которые начинаются с буквы "А" (или "а"), приводит эти строки к верхнему регистру и
     * записывает результат в файл outputFilePath.
     *
     * @param inputFilePath  путь к исходному файлу
     * @param outputFilePath путь к конечному файлу
     * @throws IOException              если файл не найден или произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если путь к файлу пустой или null
     * @see BufferedReader
     * @see FileReader
     */
    public static void filterAndCapitalize(String inputFilePath, String outputFilePath) throws IOException {
        if (inputFilePath == null || inputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Input file path must not be null or empty.");
        }

        if (outputFilePath == null || outputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Output file path must not be null or empty.");
        }

        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFilePath, StandardCharsets.UTF_8));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = inputReader.readLine()) != null) {
                if (line.startsWith("A") || line.startsWith("a")) {
                    outputWriter.write(line.toUpperCase());
                    outputWriter.newLine();
                }
            }
        }
    }
}
