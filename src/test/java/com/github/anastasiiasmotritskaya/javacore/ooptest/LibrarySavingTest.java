package com.github.anastasiiasmotritskaya.javacore.ooptest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySavingTest {
    private Library emptyLibrary;
    private Library oneBookLibrary;
    private Library threeBooksLibrary;

    private final String oneBookLibraryContent = ("""
            {
              "KU7K3MBQV9LU6" : {
                "title" : "Rage",
                "author" : "Richard Bachman",
                "year" : 1977,
                "isbn" : "KU7K3MBQV9LU6"
              }
            }""");

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        emptyLibrary = new Library(new HashMap<>());

        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        Book book_2 = new Book("The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        Book book_3 = new Book("Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");

        Map<String, Book> oneBook = new HashMap<>();

        oneBook.put(book_1.getIsbn(), book_1);

        oneBookLibrary = new Library(oneBook);

        Map<String, Book> threeBooks = new HashMap<>();

        threeBooks.put(book_1.getIsbn(), book_1);
        threeBooks.put(book_2.getIsbn(), book_2);
        threeBooks.put(book_3.getIsbn(), book_3);

        threeBooksLibrary = new Library(threeBooks);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("saveToExistingJsonFile should throw IllegalArgumentException if the file path is null or empty")
    public void saveToExistingJsonFileNullOrEmptyPathTest_IllegalArgumentException(String filePath) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> threeBooksLibrary.saveToExistingJsonFile(filePath));
        String expectedMessage = "File path must not be null or empty.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("saveToNewJsonFile should throw IllegalArgumentException if the file path is null or empty")
    public void saveToNewJsonFileNullOrEmptyPathTest_IllegalArgumentException(String filePath) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> threeBooksLibrary.saveToNewJsonFile(filePath));
        String expectedMessage = "File path must not be null or empty.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("saveToExistingJsonFile should throw IOException if file doesn't exist")
    public void saveToExistingJsonFileFileDoesNotExistTest_IOException() {
        IOException exception = assertThrows(IOException.class,
                () -> threeBooksLibrary.saveToExistingJsonFile("/nonexistent/file.json"));
        String expectedMessage = "File does not exist: '/nonexistent/file.json'";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("saveNewJsonFile should throw IOException if file already exists")
    public void saveToNewJsonFileFileExistsTest_IOException() throws IOException {
        Path existingFile = tempDir.resolve("existing.json");
        Files.createFile(existingFile);
        IOException exception = assertThrows(IOException.class,
                () -> threeBooksLibrary.saveToNewJsonFile(existingFile.toString()));
        String expectedMessage = String.format("File already exists: '%s'. " +
                "Use saveToExistingJsonFile() or choose different name.", existingFile);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("saveToExistingJsonFile should write an empty library as a { } in the existing file without throwing any exception")
    public void saveToExistingJsonFileEmptyLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("emptyLibraryFile.json");
        Files.createFile(tempPath);
        emptyLibrary.saveToExistingJsonFile(String.valueOf(tempPath));
        String content = Files.readString(tempPath);
        assertEquals("{ }", content);
    }

    @Test
    @DisplayName("saveToExistingJsonFile should write one book library as json")
    public void saveToExistingJsonFileOneBookLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("oneBookLibraryFile.json");
        Files.createFile(tempPath);
        oneBookLibrary.saveToExistingJsonFile(String.valueOf(tempPath));
        String content = Files.readString(tempPath).replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(oneBookLibraryContent, content);
    }

    @Test
    @DisplayName("saveToExistingJsonFile should write three books library as json")
    public void saveToExistingJsonFileThreeBooksLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("threeBooksLibraryFile.json");
        Files.createFile(tempPath);
        threeBooksLibrary.saveToExistingJsonFile(String.valueOf(tempPath));

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(3, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU7"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU8"));

        Book book_1 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");
        Book book_2 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU7");
        Book book_3 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU8");

        assertBookMatches(book_1, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        assertBookMatches(book_2, "The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        assertBookMatches(book_3, "Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");
    }

    @Test
    @DisplayName("saveToExistingJsonFile should rewrite data in the json")
    public void saveToExistingJsonFileRewriteTest() throws IOException {
        Path tempPath = tempDir.resolve("rewriteLibraryFile.json");
        Files.createFile(tempPath);

        oneBookLibrary.saveToExistingJsonFile(String.valueOf(tempPath));
        threeBooksLibrary.saveToExistingJsonFile(String.valueOf(tempPath));

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(3, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU7"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU8"));

        Book book_1 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");
        Book book_2 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU7");
        Book book_3 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU8");

        assertBookMatches(book_1, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        assertBookMatches(book_2, "The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        assertBookMatches(book_3, "Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("saveToExistingJsonFile should write the library in the file if there are spaces in the beginning or in the end of the file path")
    @CsvSource({
            "' test.json', 'One space in the beginning'",
            "'test.json ', 'One space in the end'",
            "'   test.json', 'Few spaces in the beginning'",
            "'test.json   ', 'Few spaces in the end'"
    })
    public void saveToExistingJsonFileSpacesLibraryTest(String filePath, String description) throws IOException {
        Path tempFile = tempDir.resolve(filePath.trim());
        Files.createFile(tempFile);
        oneBookLibrary.saveToExistingJsonFile(String.valueOf(tempFile));
        String content = Files.readString(tempFile).replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(oneBookLibraryContent, content);
    }

    @Test
    @DisplayName("saveToNewJsonFile should write an empty library as a { } in the existing file without throwing any exception")
    public void saveToNewJsonFileEmptyLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("saveToNewJsonFileEmptyLibraryFile.json");
        emptyLibrary.saveToNewJsonFile(String.valueOf(tempPath));
        assertTrue(Files.exists(tempPath));
        String content = Files.readString(tempPath);
        assertEquals("{ }", content);
    }

    @Test
    @DisplayName("saveToNewJsonFile should write a single book library in the existing file")
    public void saveToNewJsonFileOneBookLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("saveToNewJsonFileOneBookLibraryFile.json");
        oneBookLibrary.saveToNewJsonFile(String.valueOf(tempPath));
        assertTrue(Files.exists(tempPath));
        String content = Files.readString(tempPath).replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(oneBookLibraryContent, content);
    }

    @Test
    @DisplayName("saveToNewJsonFile should write a three books library in the existing file")
    public void saveToNewJsonFileThreeBookLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("saveToNewJsonFileThreeBookLibraryFile.json");
        threeBooksLibrary.saveToNewJsonFile(String.valueOf(tempPath));
        assertTrue(Files.exists(tempPath));

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(3, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU7"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU8"));

        Book book_1 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");
        Book book_2 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU7");
        Book book_3 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU8");

        assertBookMatches(book_1, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        assertBookMatches(book_2, "The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        assertBookMatches(book_3, "Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("saveToNewJsonFile should write the library in the file if there are spaces in the beginning or in the end of the file path")
    @CsvSource({
            "' test.json', 'One space in the beginning'",
            "'test.json ', 'One space in the end'",
            "'   test.json', 'Few spaces in the beginning'",
            "'test.json   ', 'Few spaces in the end'"
    })
    public void saveToNewJsonFileSpacesLibraryTest(String filePath, String description) throws IOException {
        Path tempPath = tempDir.resolve(filePath.trim());
        oneBookLibrary.saveToNewJsonFile(String.valueOf(tempPath));
        String content = Files.readString(tempPath).replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(oneBookLibraryContent, content);
    }

    /**
     * Проверяет что книга соответствует ожидаемым значениям всех полей.
     * Используется в тестах для избежания дублирования кода проверок.
     */
    private void assertBookMatches(Book book, String title, String author, int year, String isbn) {
        assertAll(
                () -> assertEquals(title, book.getTitle()),
                () -> assertEquals(author, book.getAuthor()),
                () -> assertEquals(year, book.getYear()),
                () -> assertEquals(isbn, book.getIsbn())
        );
    }
}
