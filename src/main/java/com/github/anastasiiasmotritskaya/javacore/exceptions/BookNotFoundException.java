package com.github.anastasiiasmotritskaya.javacore.exceptions;

/**
 * Исключение, выбрасываемое при попытке добавить в библиотеку книгу с уже существующим ISBN
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookNotFoundException extends LibraryException {
    /**
     * @param isbn уже существующий ISBN
     */
    public BookNotFoundException(String isbn) {
        super(String.format("Book with ISBN '%s' not found.", isbn));
    }

    /**
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
