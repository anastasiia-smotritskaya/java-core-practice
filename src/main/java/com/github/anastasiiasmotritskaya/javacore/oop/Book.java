package com.github.anastasiiasmotritskaya.javacore.oop;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, содержащий основную информацию о книге: название, автор, год издания, isbn (International Standard Book Number)
 * Методы: конструктор с валидацией параметров, геттеры, toString, equals, hashcode
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;

    public Book(String title, String author, int year, String isbn) {
        validateTitle(title);
        validateAuthor(author);
        validateYear(year);
        validateIsbn(isbn);

        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.isbn = isbn.trim();
    }

    public String getTitle() {
        return title;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title field should not be null or empty. Enter the title.");
        }
    }

    public String getAuthor() {
        return author;
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("The author field must not be empty. Enter the author's name.");
        }
    }

    public int getYear() {
        return year;
    }

    private void validateYear(int year) {
        if (year < 1457 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException(String.format("The year of publication was entered incorrectly. " +
                    "You entered: %d. Please enter the correct year of publication.", year));
        }
    }

    public String getIsbn() {
        return isbn;
    }

    private void validateIsbn(String isbn) {
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isbn='" + isbn + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
