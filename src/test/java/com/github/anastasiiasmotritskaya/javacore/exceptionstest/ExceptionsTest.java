package com.github.anastasiiasmotritskaya.javacore.exceptionstest;

import com.github.anastasiiasmotritskaya.javacore.exceptions.*;
import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.github.anastasiiasmotritskaya.javacore.oop.BookStatus.RESERVED;
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
        BookStatus status = RESERVED;
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

    @ParameterizedTest
    @DisplayName("BookNotBorrowedException(String isbn, BookStatus status) constructor test")
    @EnumSource(names = {"AVAILABLE", "RESERVED"})
    public void BookNotBorrowedExceptionTest(BookStatus status) {
        String isbn = "KU7K3MBQV9LU7";
        String expectedMessage = String.format("Book '%s' cannot be returned. Current status: '%s'", isbn, status);

        BookNotBorrowedException exception = new BookNotBorrowedException(isbn, status);

        assertTrue(exception instanceof LibraryException);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("BookNotBorrowedException(String message, Throwable cause) constructor test")
    public void BookNotBorrowedExceptionCauseTest() {
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument.");
        String expectedMessage = "This book was not borrowed.";

        BookNotBorrowedException exception = new BookNotBorrowedException(expectedMessage, cause);

        assertTrue(exception instanceof LibraryException);
        assertEquals(cause, exception.getCause());
        assertEquals(expectedMessage, exception.getMessage());
    }
}
