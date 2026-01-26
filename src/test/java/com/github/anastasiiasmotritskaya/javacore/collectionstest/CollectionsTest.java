package com.github.anastasiiasmotritskaya.javacore.collectionstest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;
import com.github.anastasiiasmotritskaya.javacore.oop.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.github.anastasiiasmotritskaya.javacore.testutil.BookAndLibraryTestUtil.assertBookMatches;
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

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("removeBooksByAuthor should throw IllegalArgumentException if the author field are null or empty")
    public void removeBooksByAuthorEmptyStringTest_IllegalArgumentException(String author) {
        String expectedMessage = "The author field must not be empty. Enter the author's name.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> threeBooksLibrary.removeBooksByAuthor(author));
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(3, threeBooksLibrary.getAllBooks().size());
    }

    @Test
    @DisplayName("removeBooksByAuthor should return null if the library is empty")
    public void removeBooksByAuthorEmptyLibraryTest() {
        String author = "Donna Louise Tartt";
        int actualCount = emptyLibrary.removeBooksByAuthor(author);
        assertEquals(0, actualCount);
    }

    @Test
    @DisplayName("removeBooksByAuthor should return 0 if there are no books written by this author")
    public void removeBooksByAuthorNoBooksByThisAuthorTest() {
        String author = "Donna Louise Tartt";
        int actualCount = threeBooksLibrary.removeBooksByAuthor(author);
        assertEquals(0, actualCount);
        assertEquals(3, threeBooksLibrary.getAllBooks().size());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("removeBooksByAuthor should return 1 if there is one book of this author in the one book library")
    @CsvSource(value = {
            "'Richard Bachman', 'Plain positive test'",
            "' Richard Bachman', 'One space in the beginning'",
            "'Richard Bachman ', 'One space in the end'",
            "'   Richard Bachman', 'Few spaces in the beginning'",
            "'Richard Bachman   ', 'Few spaces in the end'",
            "'RICHARD BACHMAN', 'Upper case'",
            "'richard bachman', 'Lower case'"
    })
    public void removeBooksByAuthorOneBookLibraryTest(String author, String description) {
        int actualCount = oneBookLibrary.removeBooksByAuthor(author);
        assertEquals(1, actualCount);
        assertEquals(0, oneBookLibrary.getAllBooks().size());
    }

    @Test
    @DisplayName("removeBooksByAuthor should return 2 if there is 2 books of this author in the three books library")
    public void removeBooksByAuthorthreeBooksLibraryTest() {
        String author = "Richard Bachman";
        int actualCount = threeBooksLibrary.removeBooksByAuthor(author);
        assertEquals(2, actualCount);
        assertEquals(1, threeBooksLibrary.getAllBooks().size());
    }

    @Test
    @DisplayName("removeBooksByAuthor should return 0 if you remove all books written by all authors")
    public void removeBooksByAuthorRemoveAllBooksTest() {
        String author_1 = "Richard Bachman";
        int actualCount_1 = threeBooksLibrary.removeBooksByAuthor(author_1);
        assertEquals(2, actualCount_1);
        assertEquals(1, threeBooksLibrary.getAllBooks().size());

        String author_2 = "Emily Brontë";
        int actualCount_2 = threeBooksLibrary.removeBooksByAuthor(author_2);
        assertEquals(1, actualCount_2);
        assertEquals(0, threeBooksLibrary.getAllBooks().size());
    }

    @Test
    @DisplayName("getBooksSortedByYear should return an empty list if the library is empty")
    public void getBooksSortedByYearEmptyLibraryTest() {
        assertEquals(new ArrayList<Book>(), emptyLibrary.getBooksSortedByYear());
    }

    @Test
    @DisplayName("getBooksSortedByYear should return one book if the library has only one book")
    public void getBooksSortedByYearOneBookLibraryTest() {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        assertEquals(List.of(book), oneBookLibrary.getBooksSortedByYear());
    }

    @Test
    @DisplayName("getBooksSortedByYear should return three books sorted by year " +
            "if the library has three books with different years of publishing")
    public void getBooksSortedByYearAllYearsAreDifferentTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        Book book_2 = new Book("The Running Man", "Richard Bachman", 1982, "KU7K3MBQV9LU7");
        Book book_3 = new Book("Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");

        List<Book> actual = threeBooksLibrary.getBooksSortedByYear();

        assertEquals(book_3, actual.get(0));
        assertEquals(book_1, actual.get(1));
        assertEquals(book_2, actual.get(2));
    }

    @Test
    @DisplayName("getBooksSortedByYear should return three books sorted by year " +
            "if the library has three books with same years of publishing")
    public void getBooksSortedByYearSameYearTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
        Book book_2 = new Book("The Running Man", "Richard Bachman", 1977, "KU7K3MBQV9LU7");
        Book book_3 = new Book("Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");

        Map<String, Book> books = new HashMap<>();
        books.put(book_1.getIsbn(), book_1);
        books.put(book_2.getIsbn(), book_2);
        books.put(book_3.getIsbn(), book_3);

        Library library = new Library(books);
        List<Book> actual = library.getBooksSortedByYear();

        assertEquals(book_3, actual.get(0));
        assertEquals(1977, actual.get(1).getYear());
        assertEquals(1977, actual.get(2).getYear());
    }

    @Test
    @DisplayName("getBooksSortedByAuthor should return empty list if the library is empty")
    public void getBooksSortedByAuthorEmptyLibraryTest() {
        assertEquals(new ArrayList<>(), emptyLibrary.getBooksSortedByAuthor());
    }

    @Test
    @DisplayName("getBooksSortedByAuthor should return books sorted by authors's names")
    public void getBooksSortedByAuthorTest() {
        List<Book> sorted = threeBooksLibrary.getBooksSortedByAuthor();
        assertEquals("Emily Brontë", sorted.getFirst().getAuthor());
        assertEquals("Richard Bachman", sorted.get(1).getAuthor());
        assertEquals("Richard Bachman", sorted.get(2).getAuthor());
    }

    @Test
    @DisplayName("getBooksSortedByTitle should return empty list if the library is empty")
    public void getBooksSortedByTitleEmptyLibraryTest() {
        assertEquals(new ArrayList<>(), emptyLibrary.getBooksSortedByTitle());
    }

    @Test
    @DisplayName("getBooksSortedByTitle should return books sorted by titles")
    public void getBooksSortedByTitleTest() {
        List<Book> sorted = threeBooksLibrary.getBooksSortedByTitle();
        assertEquals("Rage", sorted.getFirst().getTitle());
        assertEquals("The Running Man", sorted.get(1).getTitle());
        assertEquals("Wuthering Heights", sorted.get(2).getTitle());
    }

    @Test
    @DisplayName("getSortedByTitleThenAuthorThenYear should return the sorted list of books")
    public void getSortedByTitleThenAuthorThenYearTest() {
        Map<String, Book> books = new HashMap<>();
        Book book_1 = new Book("Title A", "Author D", 1986, "KU7K3MBQV9LU6");
        Book book_2 = new Book("Title B", "Author F", 1945, "KU7K3MBQV9LU7");
        Book book_3 = new Book("Title C", "Author A", 2001, "KU7K3MBQV9LU8");
        Book book_4 = new Book("Title A", "Author E", 1944, "KU7K3MBQV9LU9");
        Book book_5 = new Book("Title B", "Author F", 1977, "KU7K3MBQV9LU1");
        Book book_6 = new Book("Title C", "Author G", 1950, "KU7K3MBQV9LU2");

        books.put(book_1.getIsbn(), book_1);
        books.put(book_2.getIsbn(), book_2);
        books.put(book_3.getIsbn(), book_3);
        books.put(book_4.getIsbn(), book_4);
        books.put(book_5.getIsbn(), book_5);
        books.put(book_6.getIsbn(), book_6);

        Library library = new Library(books);

        List<Book> sorted = library.getBooksSortedByTitleThenAuthorThenYear();
        assertEquals(book_1, sorted.getFirst());
        assertEquals(book_4, sorted.get(1));
        assertEquals(book_2, sorted.get(2));
        assertEquals(book_5, sorted.get(3));
        assertEquals(book_3, sorted.get(4));
        assertEquals(book_6, sorted.get(5));
    }
}
