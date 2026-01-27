package com.github.anastasiiasmotritskaya.javacore.exceptions;

/**
 * Исключение, выбрасываемое при попытке взаимодействия библиотеки с файлом, который не существует
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class LibraryFileException extends LibraryException {
    /**
     * @param message сообщение об ошибке
     */
    public LibraryFileException(String message) {
        super(message);
    }

    /**
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public LibraryFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
