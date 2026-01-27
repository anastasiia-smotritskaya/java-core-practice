package com.github.anastasiiasmotritskaya.javacore.exceptions;

/**
 * Родительский класс для исключений классов Library и Book
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class LibraryException extends RuntimeException {
    /**
     * @param message сообщение об ошибке
     */
    public LibraryException(String message) {
        super(message);
    }

    /**
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}
