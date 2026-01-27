package com.github.anastasiiasmotritskaya.javacore.exceptions;

import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;

/**
 * Исключение, выбрасываемое при попытке вернуть книгу в бибилиотеку, которая не находится в статусе BORROWED
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookNotBorrowedException extends LibraryException {
    /**
     * @param isbn   уже существующий ISBN
     * @param status актуальный статус книги
     */
    public BookNotBorrowedException(String isbn, BookStatus status) {
        super(String.format("Book '%s' cannot be returned. Current status: '%s'", isbn, status));
    }

    /**
     * @param message сообщение об ошибке
     * @param cause   причина ошибки
     */
    public BookNotBorrowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
