package com.github.anastasiiasmotritskaya.javacore.exceptions;

/**
 * Исключение, выбрасываемое при попытке добавить книгу с уже существующим ISBN.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookAlreadyExistsException extends LibraryException {
    /**
     * @param isbn уже существующий ISBN
     */
    public BookAlreadyExistsException(String isbn) {
        super(String.format("Book with ISBN '%s' already exists.", isbn));
    }

    /**
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public BookAlreadyExistsException (String message, Throwable cause) {
        super(message, cause);
    }
}
