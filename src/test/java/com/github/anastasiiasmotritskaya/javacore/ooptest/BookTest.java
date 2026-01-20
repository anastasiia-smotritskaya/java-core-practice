package com.github.anastasiiasmotritskaya.javacore.ooptest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    @DisplayName("book getters positive test")
    public void bookGettersPositiveTest() {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        assertAll(
                () -> assertEquals("Rage", book.getTitle()),
                () -> assertEquals("Richard Bachman", book.getAuthor()),
                () -> assertEquals(1977, book.getYear()),
                () -> assertEquals("KU7K3MBQV9LU9", book.getIsbn())
        );
    }

    @Test
    @DisplayName("book toString positive test")
    public void bookToStringPositiveTest() {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        String expected = "Book{title='Rage', author='Richard Bachman', year=1977, isbn='KU7K3MBQV9LU9', " +
                "status=AVAILABLE, currentBorrower='null'}";
        assertEquals(expected, book.toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("book constructor should throw IllegalArgumentException when title is null or empty")
    public void bookNullOrEmptyTitleTest_IllegalArgumentException(String title) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(title, "Richard Bachman", 1977, "KU7K3MBQV9LU9"));
        String message = "Title field should not be null or empty. Enter the title.";
        assertEquals(message, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("book constructor should throw IllegalArgumentException when author is null or empty")
    public void bookNullOrEmptyAuthorTest_IllegalArgumentException(String author) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book("Rage", author, 1977, "KU7K3MBQV9LU9"));
        String message = "The author field must not be empty. Enter the author's name.";
        assertEquals(message, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("book year positive test")
    @MethodSource("bookYearPositiveDataProvider")
    public void bookYearPositiveTest(int year) {
        Book book = new Book("Rage", "Richard Bachman", year, "KU7K3MBQV9LU9");
        assertEquals(year, book.getYear());
    }

    public static Stream<Arguments> bookYearPositiveDataProvider() {
        return Stream.of(
                Arguments.of(1457),
                Arguments.of(1458),
                Arguments.of(LocalDate.now().getYear() - 1),
                Arguments.of(LocalDate.now().getYear())
        );
    }

    @ParameterizedTest
    @DisplayName("book constructor should throw IllegalArgumentException when year < 1457 or year > current year")
    @MethodSource("bookYearNegativeDataProvider")
    public void bookYearTest_IllegalArgumentException(int year) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book("Rage", "Richard Bachman", year, "KU7K3MBQV9LU9"));
        String message = String.format("The year of publication was entered incorrectly. " +
                "You entered: %d. Please enter the correct year of publication.", year);
        assertEquals(message, exception.getMessage());
    }

    public static Stream<Arguments> bookYearNegativeDataProvider() {
        return Stream.of(
                Arguments.of(-2024),
                Arguments.of(0),
                Arguments.of(1400),
                Arguments.of(LocalDate.now().getYear() + 1),
                Arguments.of(2500)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("book constructor should throw IllegalArgumentException when ISBN is null or empty")
    public void bookISBNNullAndEmptyTest_IllegalArgumentException(String isbn) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book("Rage", "Richard Bachman", 1977, isbn));
        String message = "The International Standard Book Number field must not be empty. " +
                "Enter the International Standard Book Number.";
        assertEquals(message, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("book constructor should throw IllegalArgumentException when ISBN is not 13 symbols length")
    @CsvSource({
            "'KU7K3MBQV9LU','Less than 13 symbols in ISBN'",
            "'KU7K3MBQV9LU900','More than 13 symbols in ISBN'"
    })
    public void bookISBNNegativeTest_IllegalArgumentException(String isbn, String description) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book("Rage", "Richard Bachman", 1977, isbn));
        String message = String.format("The International Standard Book Number was entered incorrectly (ISBN-13). " +
                "You entered: %s. Please enter the correct International Standard Book Number (13 symbols).", isbn);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("book constructor should throw IllegalArgumentException when ISBN has special characters")
    public void bookISBNSpecialCharactersNegativeTest_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book("Rage", "Richard Bachman", 1977, "KU!K@M#$V5LU9"));
        String message = "ISBN must contain only letters and digits. Got: KU!K@M#$V5LU9";
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("books with different titles are equal and have the same hashcode when their ISBN are equal")
    public void bookTitleEqualHashcodePositiveTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("The Long Walk", "Richard Bachman", 1977, "KU7K3MBQV9LU9");

        assertAll(
                () -> assertEquals(book_1, book_2),
                () -> assertEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different titles are not equal and have different hashcodes when their ISBNs are different")
    public void bookTitleEqualHashcodeNegativeTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("The Long Walk", "Richard Bachman", 1977, "KU7K3MBQV9LU8");

        assertAll(
                () -> assertNotEquals(book_1, book_2),
                () -> assertNotEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different authors are equal and have the same hashcode when their ISBN are equal")
    public void bookAuthorEqualHashcodePositiveTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("Rage", "Stephen King", 1977, "KU7K3MBQV9LU9");

        assertAll(
                () -> assertEquals(book_1, book_2),
                () -> assertEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different authors are not equal and have different hashcodes when their ISBNs are different")
    public void bookAuthorEqualHashcodeNegativeTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("Rage", "Stephen King", 1977, "KU7K3MBQV9LU8");

        assertAll(
                () -> assertNotEquals(book_1, book_2),
                () -> assertNotEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different years are equal and have the same hashcode when their ISBN are equal")
    public void bookYearEqualHashcodePositiveTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("Rage", "Richard Bachman", 1976, "KU7K3MBQV9LU9");

        assertAll(
                () -> assertEquals(book_1, book_2),
                () -> assertEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different years are not equal and have different hashcodes when their ISBNs are different")
    public void bookYearEqualHashcodeNegativeTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("Rage", "Richard Bachman", 1976, "KU7K3MBQV9LU8");

        assertAll(
                () -> assertNotEquals(book_1, book_2),
                () -> assertNotEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with the same ISBN are equal")
    public void bookISBNEqualHashcodePositiveTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");

        assertAll(
                () -> assertEquals(book_1, book_2),
                () -> assertEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("books with different ISBNs are not equal and have different hashcodes")
    public void bookISBNEqualHashcodeNegativeTest() {
        Book book_1 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        Book book_2 = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU8");

        assertAll(
                () -> assertNotEquals(book_1, book_2),
                () -> assertNotEquals(book_1.hashCode(), book_2.hashCode())
        );
    }

    @Test
    @DisplayName("A new book has default status AVAILABLE")
    public void bookDefaultStatusTest(){
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        assertEquals(BookStatus.AVAILABLE, book.getStatus());
    }

    @Test
    @DisplayName("setBookStatus changes a book status")
    public void bookSetStatusTest(){
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        assertEquals(BookStatus.AVAILABLE, book.getStatus());

        book.setStatus(BookStatus.BORROWED);
        assertEquals(BookStatus.BORROWED, book.getStatus());

        book.setStatus(BookStatus.RESERVED);
        assertEquals(BookStatus.RESERVED, book.getStatus());

        book.setStatus(BookStatus.AVAILABLE);
        assertEquals(BookStatus.AVAILABLE, book.getStatus());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("borrow should throw IllegalArguments exception if the borrower's name is null or empty")
    public void borrowNullOrEmptyBorrowersNameTest_IllegalArgumentException(String borrowersName) {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> book.borrow(borrowersName));
        String expectedMessage = "Borrower name must not be null or empty. Enter the borrower's name.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("borrow should throw IllegalStateException if the book is borrowed or reserved")
    @EnumSource(names = {"BORROWED", "RESERVED"})
    public void borrowStatusNegativeTest_IllegalStateException(BookStatus status) {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        book.setStatus(status);
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> book.borrow("Jane Doe"));
        String expectedMessage = String.format("This book status: %s", book.getStatus());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("borrow should change the book status to BORROWED and save the borrower's name when someone borrows the book")
    @CsvSource({
            "'Jane Doe','Plain positive test'",
            "' Jane Doe','One space in the beginning'",
            "'Jane Doe ','One space in the end'",
            "'   Jane Doe','Few spaces in the beginning'",
            "'Jane Doe   ','Few spaces in the end''",
    })
    public void borrowStatusPositiveTest(String borrower, String description) {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        book.borrow(borrower);
        assertEquals(BookStatus.BORROWED, book.getStatus());
        assertEquals(borrower.trim(), book.getCurrentBorrower());
    }

    @ParameterizedTest
    @DisplayName("return should throw IllegalStateException if the book is borrowed or reserved if thw book is AVAILABLE or RESERVED")
    @EnumSource(names = {"AVAILABLE", "RESERVED"})
    public void returnStatusNegativeTest_IllegalStateException(BookStatus status) {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        book.setStatus(status);
        IllegalStateException exception = assertThrows(IllegalStateException.class, book::returnBook);
        String expectedMessage = String.format("This book has not been issued. Current status: %s", status);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("return should change book status to AVAILABLE and save the borrower's name as null if book status is BORROWED")
    public void returnStatusPositiveTest() {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        book.borrow("Jane Doe");
        book.returnBook();
        assertEquals(BookStatus.AVAILABLE, book.getStatus());
        assertNull(book.getCurrentBorrower());
    }

    @ParameterizedTest
    @DisplayName("isAvailable returns true if book is AVAILABLE and false in the other cases")
    @MethodSource("isAvailableDataProvider")
    public void isAvailableTest(BookStatus status, boolean isAvailable) {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        book.setStatus(status);
        assertEquals(isAvailable, book.isAvailable());
    }

    static Stream<Arguments> isAvailableDataProvider(){
        return Stream.of(
                Arguments.of(BookStatus.AVAILABLE, true),
                Arguments.of(BookStatus.BORROWED, false),
                Arguments.of(BookStatus.RESERVED, false)
        );
    }

    @Test
    @DisplayName("getCurrentBorrower return null if there is no borrower anf the name of borrower if there is a borrower")
    public void getCurrentBorrowerTest() {
        Book book = new Book("Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU9");
        assertNull(book.getCurrentBorrower());
        book.borrow("Jane Doe");
        assertEquals("Jane Doe", book.getCurrentBorrower());
        book.returnBook();
        assertNull(book.getCurrentBorrower());
    }
}
