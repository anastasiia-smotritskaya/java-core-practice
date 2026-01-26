package com.github.anastasiiasmotritskaya.javacore.oop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.anastasiiasmotritskaya.javacore.util.BookValidator;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Представляет книгу в библиотечной системе.
 * Две книги считаются равными если у них одинаковый ISBN.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Book extends LibraryItem implements Borrowable {
    private String title;
    private String author;
    private int year;
    private String isbn;
    private BookStatus status;
    private String currentBorrower;

    /**
     * Пустой конструктор класса Book необходим для десериализации json
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
        BookValidator.validateTitle(title);
        BookValidator.validateAuthor(author);
        BookValidator.validateYear(year);
        BookValidator.validateIsbn(isbn);

        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.isbn = isbn.trim();
        this.status = BookStatus.AVAILABLE;
        this.currentBorrower = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    /**
     * Выдаёт объект читателю.
     *
     * @param borrowerName имя читателя
     * @throws IllegalStateException если объект уже выдан или забронирован
     * @throws IllegalStateException если книга уже в статусе BORROWED или RESERVED
     */
    @Override
    public void borrow(String borrowerName) {
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrower name must not be null or empty. Enter the borrower's name.");
        }
        if (!isAvailable()) {
            throw new IllegalStateException("This book status: " + this.status);
        }
        this.status = BookStatus.BORROWED;
        this.currentBorrower = borrowerName.trim();
    }

    /**
     * Возвращает объект в библиотеку.
     *
     * @throws IllegalStateException если объект не был выдан
     */
    @Override
    public void returnBook() {
        if (status != BookStatus.BORROWED) {
            throw new IllegalStateException("This book has not been issued. Current status: " + status);
        }
        this.status = BookStatus.AVAILABLE;
        this.currentBorrower = null;
    }

    /**
     * Проверяет доступен ли объект для выдачи.
     *
     * @return true если объект доступен (статус AVAILABLE), false если книга в статусе BORROWED или RESERVED
     */
    @Override
    @JsonIgnore
    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    @Override
    public String getCurrentBorrower() {
        return this.currentBorrower;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                ", currentBorrower='" + currentBorrower + '\'' +
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

    @Override
    @JsonIgnore
    public String getDescription() {
        return String.format("Title: '%s'%nAuthor: %s%nStatus: %s%n", title, author, status);
    }
}
