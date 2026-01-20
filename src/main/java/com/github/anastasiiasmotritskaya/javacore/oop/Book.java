package com.github.anastasiiasmotritskaya.javacore.oop;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Представляет книгу в библиотечной системе.
 * Класс является immutable (неизменяемым) - после создания поля нельзя изменить.
 * Две книги считаются равными если у них одинаковый ISBN.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private String isbn;
    private BookStatus status;

    /**
     * Пустой конструктор класса Book необходим для сесериализации json
     */
    public Book() {
    }

    /**
     * Создаёт новую книгу с указанными параметрами.
     *
     * @param title  название книги (не может быть null или пустым)
     * @param author автор книги (не может быть null или пустым)
     * @param year   год издания (должен быть между 1457 и текущим годом)
     * @param isbn   уникальный идентификатор книги, 13 символов (не может быть null или пустым)
     * @throws IllegalArgumentException если какой-либо параметр невалиден
     */
    public Book(String title, String author, int year, String isbn) {
        validateTitle(title);
        validateAuthor(author);
        validateYear(year);
        validateIsbn(isbn);

        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.isbn = isbn.trim();
        this.status = BookStatus.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Валидация название книги.
     *
     * @throws IllegalArgumentException если название книги null или empty
     */
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title field should not be null or empty. Enter the title.");
        }
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Валидация имени автора.
     *
     * @throws IllegalArgumentException если название имя автора null или empty
     */
    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("The author field must not be empty. Enter the author's name.");
        }
    }

    public int getYear() {
        return year;
    }

    /**
     * Валидация года издания.
     *
     * @throws IllegalArgumentException если название год издания меньше 1457 или больше текущего года
     */
    private void validateYear(int year) {
        if (year < 1457 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException(String.format("The year of publication was entered incorrectly. " +
                    "You entered: %d. Please enter the correct year of publication.", year));
        }
    }

    public String getIsbn() {
        return isbn;
    }

    /**
     * Валидация ISBN - The International Standard Book Number.
     *
     * @throws IllegalArgumentException если isbn null, empty, длине не равна 13 символам
     *                                  или содержит специальные символы
     */
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

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
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
