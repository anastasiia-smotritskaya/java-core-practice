package com.github.anastasiiasmotritskaya.javacore.util;

import java.time.LocalDate;

/**
 * Валидация основных полей класса Book: title, author, year, isbn
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public final class BookValidator {
    /**
     * Приватный конструктор для предотвращения создания экземпляров.
     */
    private BookValidator() {
    }

    /**
     * Валидация название книги.
     *
     * @throws IllegalArgumentException если название книги null или empty
     */
    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title field should not be null or empty. Enter the title.");
        }
    }

    /**
     * Валидация имени автора.
     *
     * @throws IllegalArgumentException если название имя автора null или empty
     */
    public static void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("The author field must not be empty. Enter the author's name.");
        }
    }

    /**
     * Валидация года издания.
     *
     * @throws IllegalArgumentException если название год издания меньше 1457 или больше текущего года
     */
    public static void validateYear(int year) {
        if (year < 1457 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException(String.format("The year of publication was entered incorrectly. " +
                    "You entered: %d. Please enter the correct year of publication.", year));
        }
    }

    /**
     * Валидация ISBN - The International Standard Book Number.
     *
     * @throws IllegalArgumentException если isbn null, empty, длине не равна 13 символам
     *                                  или содержит специальные символы
     */
    public static void validateIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("The International Standard Book Number field must not be empty. " +
                    "Enter the International Standard Book Number.");
        }

        if (isbn.trim().length() != 13) {
            throw new IllegalArgumentException(String.format("The International Standard Book Number was entered incorrectly (ISBN-13). " +
                    "You entered: %s. Please enter the correct International Standard Book Number (13 symbols).", isbn));
        }

        if (!isbn.trim().matches("[A-Za-z0-9]{13}")) {
            throw new IllegalArgumentException(
                    "ISBN must contain only letters and digits. Got: " + isbn.trim());
        }
    }
}
