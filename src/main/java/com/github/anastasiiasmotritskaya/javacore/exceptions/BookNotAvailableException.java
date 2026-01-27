package com.github.anastasiiasmotritskaya.javacore.exceptions;

import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;

/**
 * Исключение, выбрасываемое при попытке взять из библиотеки книгу, которая уже была в статусе BORROWED или RESERVED
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookNotAvailableException extends LibraryException {
    /**
     * @param isbn уже существующий ISBN
     * @param status текущий статус книги
     */
    public BookNotAvailableException(String isbn, BookStatus status) {
        super(String.format("Book with ISBN '%s' is not available. Current status: '%s'.", isbn, status));
    }

    /**
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public BookNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
