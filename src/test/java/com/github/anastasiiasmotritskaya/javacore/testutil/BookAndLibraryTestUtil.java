package com.github.anastasiiasmotritskaya.javacore.testutil;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Вспомогательный класс для тестовых методов, отнясящихся к проверке классов Book и Library
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookAndLibraryTestUtil {
    /**
     * Проверяет что книга соответствует ожидаемым значениям всех полей.
     * Используется в тестах для избежания дублирования кода проверок.
     */
    public static void assertBookMatches(Book book, String title, String author, int year, String isbn) {
        assertAll(
                () -> assertEquals(title, book.getTitle()),
                () -> assertEquals(author, book.getAuthor()),
                () -> assertEquals(year, book.getYear()),
                () -> assertEquals(isbn, book.getIsbn())
        );
    }
}
