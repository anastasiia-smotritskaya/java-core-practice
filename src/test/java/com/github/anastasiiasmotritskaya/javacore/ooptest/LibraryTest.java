package com.github.anastasiiasmotritskaya.javacore.ooptest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Map<String, Book> books;

    @BeforeEach
    void setUp() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        Book book_2 = new Book("The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        Book book_3 = new Book("Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");
        Book book_4 = new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");
        Book book_5 = new Book("The Double", "José de Sousa Saramago", 2002, "KU7K3MBQV9LU1");
        Book book_6 = new Book("The Double", "Fyodor Dostoyevsky", 1846, "KU7K3MBQV9LU2");

        books = new HashMap<>();

        books.put(book_1.getIsbn(), book_1);
        books.put(book_2.getIsbn(), book_2);
        books.put(book_3.getIsbn(), book_3);
        books.put(book_4.getIsbn(), book_4);
        books.put(book_5.getIsbn(), book_5);
        books.put(book_6.getIsbn(), book_6);

        library = new Library(books);
    }

    @Test
    @DisplayName("addBook should throw IllegalArgumentException if a book with the same isbn is already in the library")
    public void addBookISBNAlreadyExistsTest_IllegalArgumentException() {
        Book newBook = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> library.addBook(newBook));
        String message = String.format("Key '%s' already exists with value: %s", newBook.getIsbn(),
                books.get(newBook.getIsbn()).toString());
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("addBook should add the book in the library when it has unique isbn")
    public void addBookPositiveTest() {
        Book newBook = new Book("The Long Walk", "Richard Bachman", 1979, "KU7K3MBQV9LU0");
        library.addBook(newBook);
        assertEquals(newBook, library.findBookByISBN("KU7K3MBQV9LU0"));
    }

    @Test
    @DisplayName("deleteBook should throw IllegalArgumentException if there is no book with such isbn in the library")
    public void deleteBookNoISBNTest_IllegalArgumentException() {
        String isbn = "KU7K3MBQV9LU0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> library.deleteBook(isbn));
        String message = String.format("There is no book with this number (%s). Please check the number.", isbn);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("deleteBook should delete books from the library if there is book with such isbn")
    public void deleteBookPositiveTest() {
        String isbn = "KU7K3MBQV9LU7";
        library.deleteBook(isbn);
        assertThrows(IllegalArgumentException.class, () -> library.findBookByISBN(isbn));
    }

    @Test
    @DisplayName("findBookByISBN should throw IllegalArgumentException if there is no book with such isbn in the library")
    public void findBookByISBNNoISBNTest_IllegalArgumentException() {
        String isbn = "KU7K3MBQV9LU0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> library.findBookByISBN(isbn));
        String message = String.format("There is no book with this number (%s). Please check the number.", isbn);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("findBookByISBN should return book if there is a book with such isbn in the library")
    public void findBookByISBNPositiveTest() {
        String isbn = "KU7K3MBQV9LU9";
        Book expected = new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");
        assertEquals(expected, library.findBookByISBN(isbn));
    }

    @Test
    @DisplayName("findBookByAuthor should return empty list if there are no books of this author in the library")
    public void findBookByAuthorNoBooksTest() {
        String author = "Edgar Allan Poe";
        assertEquals(new ArrayList<>(), library.findBookByAuthor(author));
    }

    @Test
    @DisplayName("findBookByAuthor should return list of one book if there is one book of this author in the library")
    public void findBookByAuthorOneBookPositiveTest() {
        String author = "John Steinbeck";
        Book expected = new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");
        assertEquals(List.of(expected), library.findBookByAuthor(author));
    }

    @Test
    @DisplayName("findBookByAuthor should return list of books if there are several books of this author in the library")
    public void findBookByAuthorSeveralBooksPositiveTest() {
        String author = "Richard Bachman";
        List<Book> expected = List.of(new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6"),
                new Book("The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7"));

        List<Book> actual = library.findBookByAuthor(author);
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    @DisplayName("findBookByTitle should return empty list if there are no books with such title in the library")
    public void findBookByTitleNoBooksTest() {
        String title = "Die Verwandlung";
        assertEquals(new ArrayList<>(), library.findBookByTitle(title));
    }

    @Test
    @DisplayName("findBookByTitle should return list of one book if there is one book with such title in the library")
    public void findBookByTitleOneBookPositiveTest() {
        String title = "The Grapes of Wrath";
        Book expected = new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");
        assertEquals(List.of(expected), library.findBookByTitle(title));
    }

    @Test
    @DisplayName("findBookByTitle should return list of books if there are several books with such title in the library")
    public void findBookByTitleSeveralBooksPositiveTest() {
        String title = "The Double";
        List<Book> expected = List.of(new Book("The Double", "José de Sousa Saramago", 2002, "KU7K3MBQV9LU1"),
                new Book("The Double", "Fyodor Dostoyevsky", 1846, "KU7K3MBQV9LU2"));

        List<Book> actual = library.findBookByTitle(title);
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    @DisplayName("getAllBooks shoul return all books from the library")
    public void getAllBooksTest() {
        Map<String, Book> expected = Map.ofEntries(
                Map.entry("KU7K3MBQV9LU6", new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6")),
                Map.entry("KU7K3MBQV9LU7", new Book("The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7")),
                Map.entry("KU7K3MBQV9LU8", new Book("Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8")),
                Map.entry("KU7K3MBQV9LU9", new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9")),
                Map.entry("KU7K3MBQV9LU1", new Book("The Double", "José de Sousa Saramago", 2002, "KU7K3MBQV9LU1")),
                Map.entry("KU7K3MBQV9LU2", new Book("The Double", "Fyodor Dostoyevsky", 1846, "KU7K3MBQV9LU2")));

        Map<String, Book> actual = library.getAllBooks();
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("deleteBook should throw IllegalArgumentException if isbn is null or empty")
    public void deleteBookNullOrEmptyTest_IllegalArgumentException(String isbn) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.deleteBook(isbn));
        String expectedMessage = "The International Standard Book Number field must not be empty. " +
                "Enter the International Standard Book Number.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("findBookByISBN should throw IllegalArgumentException if isbn is null or empty")
    public void findBookByISBNNullOrEmptyTest_IllegalArgumentException(String isbn) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.findBookByISBN(isbn));
        String expectedMessage = "The International Standard Book Number field must not be empty. " +
                "Enter the International Standard Book Number.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("deleteBook should throw IllegalArgumentException if isbn length is less or more than 13 symbols")
    @CsvSource({
            "'KU7K3MBQV9LU','Less than 13 symbols in ISBN'",
            "'KU7K3MBQV9LU900','More than 13 symbols in ISBN'"
    })
    public void deleteBookWrongSizeISBNTest_IllegalArgumentException(String isbn) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.deleteBook(isbn));
        String expectedMessage = String.format("The International Standard Book Number was entered incorrectly (ISBN-13). " +
                "You entered: %s. Please enter the correct International Standard Book Number (13 symbols).", isbn);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("findBookByISBN should throw IllegalArgumentException if isbn length is less or more than 13 symbols")
    @CsvSource({
            "'KU7K3MBQV9LU','Less than 13 symbols in ISBN'",
            "'KU7K3MBQV9LU900','More than 13 symbols in ISBN'"
    })
    public void findBookByISBNWrongSizeISBNTest_IllegalArgumentException(String isbn) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.findBookByISBN(isbn));
        String expectedMessage = String.format("The International Standard Book Number was entered incorrectly (ISBN-13). " +
                "You entered: %s. Please enter the correct International Standard Book Number (13 symbols).", isbn);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("deleteBook should throw IllegalArgumentException if isbn has special characters")
    public void deleteBookSpecCharTest_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.deleteBook("K@7#KM$Q&9*U1"));
        String expectedMessage = "ISBN must contain only letters and digits. Got: K@7#KM$Q&9*U1";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("findBookByISBN should throw IllegalArgumentException if isbn has special characters")
    public void findBookByISBNSpecCharTest_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.findBookByISBN("K@7#KM$Q&9*U1"));
        String expectedMessage = "ISBN must contain only letters and digits. Got: K@7#KM$Q&9*U1";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("findBookByAuthor should throw IllegalArgumentException if author is null or empty")
    public void findBookByAuthorNullOrEmptyTest_IllegalArgumentException(String author) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.findBookByAuthor(author));
        String expectedMessage = "The author field must not be empty. Enter the author's name.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("findBookByTitle should throw IllegalArgumentException if author is null or empty")
    public void findBookByTitleNullOrEmptyTest_IllegalArgumentException(String author) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.findBookByTitle(author));
        String expectedMessage = "Title field should not be null or empty. Enter the title.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
