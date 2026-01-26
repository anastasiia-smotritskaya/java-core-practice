package com.github.anastasiiasmotritskaya.javacore.exceptionstest;

import com.github.anastasiiasmotritskaya.javacore.exceptions.BookAlreadyExistsException;
import com.github.anastasiiasmotritskaya.javacore.exceptions.BookNotAvailableException;
import com.github.anastasiiasmotritskaya.javacore.exceptions.BookNotFoundException;
import com.github.anastasiiasmotritskaya.javacore.exceptions.LibraryException;
import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionsTest {
    @Test
    @DisplayName("BookAlreadyExistsException(String isbn) constructor test")
    public void BookAlreadyExistsExceptionTest() {
        String isbn = "KU7K3MBQV9LU7";
        String expectedMessage = String.format("Book with ISBN '%s' already exists.", isbn);

        BookAlreadyExistsException exception = new BookAlreadyExistsException(isbn);

        assertTrue(exception instanceof LibraryException);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookAlreadyExistsException(String message, Throwable cause) constructor test")
    public void BookAlreadyExistsCauseExceptionTest() {
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument.");
        String expectedMessage = "This book already exists.";

        BookAlreadyExistsException exception = new BookAlreadyExistsException(expectedMessage, cause);

        assertTrue(exception instanceof LibraryException);
        assertEquals(cause, exception.getCause());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookNotAvailableException(String isbn, BookStatus status) constructor test")
    public void BookNotAvailableExceptionTest() {
        String isbn = "KU7K3MBQV9LU7";
        BookStatus status = BookStatus.RESERVED;
        String expectedMessage = String.format("Book with ISBN '%s' is not available. Current status: '%s'.", isbn, status);

        BookNotAvailableException exception = new BookNotAvailableException(isbn, status);

        assertTrue(exception instanceof LibraryException);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookNotAvailableException(String message, Throwable cause) constructor test")
    public void BookNotAvailableExceptionCauseTest() {
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument.");
        String expectedMessage = "This book is not available.";

        BookNotAvailableException exception = new BookNotAvailableException(expectedMessage, cause);

        assertTrue(exception instanceof LibraryException);
        assertEquals(cause, exception.getCause());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookNotFoundException(String isbn) constructor test")
    public void BookNotFoundExceptionTest() {
        String isbn = "KU7K3MBQV9LU7";
        String expectedMessage = String.format("Book with ISBN '%s' not found.", isbn);

        BookNotFoundException exception = new BookNotFoundException(isbn);

        assertTrue(exception instanceof LibraryException);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookNotFoundException(String message, Throwable cause) constructor test")
    public void BookNotFoundExceptionCauseTest() {
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument.");
        String expectedMessage = "This book is not found.";

        BookNotFoundException exception = new BookNotFoundException(expectedMessage, cause);

        assertTrue(exception instanceof LibraryException);
        assertEquals(cause, exception.getCause());
        assertEquals(expectedMessage, exception.getMessage());
    }
}
