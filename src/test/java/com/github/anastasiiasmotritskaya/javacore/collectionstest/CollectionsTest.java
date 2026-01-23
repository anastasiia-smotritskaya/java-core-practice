package com.github.anastasiiasmotritskaya.javacore.collectionstest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;
import com.github.anastasiiasmotritskaya.javacore.oop.Library;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionsTest {
    private static final int gutenbergBibleYear = 1457;
    private static final int averageYear = 2001;
    private static final int currentYear = LocalDate.now().getYear();
    private static Library emptyLibrary;
    private static Library oneBookLibrary;
    private static Library threeBooksLibrary;

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

    @ParameterizedTest(name = "[{index}] {3}")
    @DisplayName("findBooksByYear should throw IllegalArgumentException if fromYear or toYear is out of range " +
            "or fromYear > toYear")
    @MethodSource("yearNegativeTestDataProvider")
    public void findBooksByYearTest_IllegalArgumentException(int fromYear, int toYear, String expectedMessage, String description) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> emptyLibrary.findBooksByYearRange(fromYear, toYear));
        assertEquals(expectedMessage, exception.getMessage());
    }

    static Stream<Arguments> yearNegativeTestDataProvider() {
        return Stream.of(
                Arguments.of(gutenbergBibleYear - 1, averageYear,
                        String.format("The year of publication was entered incorrectly. " +
                                "You entered: %d. Please enter the correct year of publication.", gutenbergBibleYear - 1),
                        String.format("fromYear is less than %d", gutenbergBibleYear)),
                Arguments.of(currentYear + 1, currentYear + 2,
                        String.format("The year of publication was entered incorrectly. " +
                                "You entered: %d. Please enter the correct year of publication.", currentYear + 1),
                        String.format("fromYear is more than %d", currentYear)),
                Arguments.of(averageYear, currentYear + 1,
                        String.format("The year of publication was entered incorrectly. " +
                                "You entered: %d. Please enter the correct year of publication.", currentYear + 1),
                        String.format("toYear is more than %d", currentYear)),
                Arguments.of(currentYear, averageYear, String.format("The toYear is less than fromYear. " +
                                "You entered: fromYear: %d, toYear: %d. Please enter the correct toYear of publication.",
                        currentYear, averageYear), "fromYear > toYear")
        );
    }

    @ParameterizedTest
    @DisplayName("findBooksByYear should not throw IllegalArgumentException if fromYear and toYear are inside of range")
    @MethodSource("yearPositiveTestDataProvider")
    public void findBooksByYearPositiveTest(int fromYear, int toYear) {
        assertDoesNotThrow(() -> emptyLibrary.findBooksByYearRange(fromYear, toYear));
    }

    static Stream<Arguments> yearPositiveTestDataProvider() {
        return Stream.of(
                Arguments.of(gutenbergBibleYear, currentYear),
                Arguments.of(gutenbergBibleYear + 1, currentYear - 1),
                Arguments.of(currentYear - 1, currentYear),
                Arguments.of(currentYear, currentYear)
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @DisplayName("findBooksByYear should return empty map if there are no books published in this year range")
    @MethodSource("findBooksByYearEmptyReturnDataProvider")
    public void findBooksByYearEmptyReturnTest(int fromYear, int toYear, Library library, String description) {
        assertEquals(new ArrayList<>(), library.findBooksByYearRange(fromYear, toYear));
    }

    static Stream<Arguments> findBooksByYearEmptyReturnDataProvider() {
        return Stream.of(
                Arguments.of(1950, 2000, emptyLibrary, "Empty library: no books published in 1950-2000"),
                Arguments.of(1900, 1950, oneBookLibrary, "One book library: no books published in 1900-1950"),
                Arguments.of(1900, 1950, threeBooksLibrary, "Three books library: no books published in 1900-1950")
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @DisplayName("findBooksByYear should return a list of books published in the year range if they are in the library")
    @MethodSource("findBooksByYearReturnsCorrectBooksDataProvider")
    public void findBooksByYearReturnsCorrectBooksTest(Library library, int fromYear, int toYear, String description) {
        List<Book> books = library.findBooksByYearRange(fromYear, toYear);
        for (Book book : books) {
            assertTrue(book.getYear() >= fromYear && book.getYear() <= toYear);
        }
    }

    static Stream<Arguments> findBooksByYearReturnsCorrectBooksDataProvider() {
        return Stream.of(
                Arguments.of(oneBookLibrary, 1950, 2000,
                        "One book found in the library with one book (fromYear and twoYear are different"),
                Arguments.of(oneBookLibrary, 1977, 1977,
                        "One book found in the library with one book (fromYear and twoYear are the same)"),
                Arguments.of(threeBooksLibrary, 1850, 1900,
                        "One book found in the library with three books"),
                Arguments.of(threeBooksLibrary, 1950, 2000,
                        "Two books found in the library with three books")
        );
    }

    @Test
    @DisplayName("getAllUniqueAuthors should return an empty set if the library is empty")
    public void getAllUniqueAuthorsEmptyLibraryTest() {
        Set<String> actual = emptyLibrary.getAllUniqueAuthors();
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("getAllUniqueAuthors should return a set of authors if there are any authors")
    @MethodSource("getAllUniqueAuthorsDataProvider")
    public void getAllUniqueAuthorsTest(Library library, int expectedSize, String description) {
        Set<String> actual = library.getAllUniqueAuthors();

        assertEquals(expectedSize, actual.size());

        for (Book book : library.getAllBooks().values()) {
            assertTrue(actual.contains(book.getAuthor()));
        }
    }

    static Stream<Arguments> getAllUniqueAuthorsDataProvider() {
        return Stream.of(
                Arguments.of(oneBookLibrary, 1, "There is one author in the library with one book"),
                Arguments.of(threeBooksLibrary, 2, "There are two authors in library with three books")
        );
    }

    @Test
    @DisplayName("countBooksByStatus should return a map with null values if the library is empty")
    public void countBooksByStatusEmptyLibraryTest() {
        Map<BookStatus, Integer> statusMap = emptyLibrary.countBooksByStatus();
        assertEquals(0, statusMap.size());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("countBooksByStatus should return a map with proper values if the library has one book")
    @MethodSource("countBooksByStatusDataProvider")
    public void countBooksByStatusOneBookLibraryTest(BookStatus status, String description) {
        oneBookLibrary.findBookByISBN("KU7K3MBQV9LU6").setStatus(status);
        Map<BookStatus, Integer> statusMap = oneBookLibrary.countBooksByStatus();
        assertEquals(1, statusMap.size());
        assertEquals(1, statusMap.get(status));
    }

    static Stream<Arguments> countBooksByStatusDataProvider() {
        return Stream.of(
                Arguments.of(BookStatus.AVAILABLE, "One book: status AVAILABLE"),
                Arguments.of(BookStatus.RESERVED, "One book: status RESERVED"),
                Arguments.of(BookStatus.BORROWED, "One book: status BORROWED")
        );
    }

    @Test
    @DisplayName("countBooksByStatus should return a map with proper values if the library has several books")
    public void countBooksByStatusThreeBooksLibraryTest() {
        threeBooksLibrary.findBookByISBN("KU7K3MBQV9LU7").setStatus(BookStatus.RESERVED);
        Map<BookStatus, Integer> statusMap = threeBooksLibrary.countBooksByStatus();
        assertEquals(2, statusMap.size());
        assertEquals(2, statusMap.get(BookStatus.AVAILABLE));
        assertEquals(1, statusMap.get(BookStatus.RESERVED));
    }
}
