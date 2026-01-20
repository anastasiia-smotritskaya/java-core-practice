package com.github.anastasiiasmotritskaya.javacore.ooptest;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;
import com.github.anastasiiasmotritskaya.javacore.oop.Library;
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
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryLoadingTest {
    private final Library emptyLibrary = new Library(new HashMap<>());

    @TempDir
    Path tempDir;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("loadFromJsonFile should throw IllegalArgumentException if the file path is null or empty")
    public void loadFromJsonFileNullOrEmptyPathTest_IllegalArgumentException(String filePath) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> emptyLibrary.loadFromJsonFile(filePath));
        String expectedMessage = "File path must not be null or empty.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("loadFromJsonFile should throw IOException if file doesn't exist")
    public void loadFromJsonFileFileDoesNotExistTest_IOException() {
        IOException exception = assertThrows(IOException.class,
                () -> emptyLibrary.loadFromJsonFile("/nonexistent/file.json"));
        String expectedMessage = "File does not exist: '/nonexistent/file.json'";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("loadFromJson should write to the library an empty map if json file has zero bytes")
    public void loadFromJsonZeroBytesFileTest() throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonZeroBytesFile.json");
        Files.write(tempPath, new byte[0]);
        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));
        assertEquals(new HashMap<>(), emptyLibrary.getAllBooks());
    }

    @Test
    @DisplayName("loadFromJson should write to the library an empty map if json file is empty")
    public void loadFromJsonEmptyFileTest() throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonEmptyFile.json");
        String jsonContent = "";
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);
        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));
        assertEquals(new HashMap<>(), emptyLibrary.getAllBooks());
    }

    @Test
    @DisplayName("loadFromJson should return an empty map if there are empty json in json file")
    public void loadFromJsonEmptyJsonFileTest() throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonEmptyFile.json");
        String jsonContent = "{}";
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);
        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));
        assertEquals(new HashMap<>(), emptyLibrary.getAllBooks());
    }

    @Test
    @DisplayName("loadFromJson should return a map with one book if there is only one book in json file")
    public void loadFromJsonSingleBookLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonSingleBookFile.json");
        String jsonContent = """
                {
                  "KU7K3MBQV9LU6" : {
                    "title" : "Rage",
                    "author" : "Richard Bachman",
                    "year" : 1977,
                    "isbn" : "KU7K3MBQV9LU6"
                  }
                }
                """;

        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(1, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));

        Book book = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");
        assertBookMatches(book, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
    }

    @Test
    @DisplayName("loadFromJson should return a map with three book if there are three books in json file")
    public void loadFromJsonThreeBooksLibraryTest() throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonThreeBooksFile.json");
        String jsonContent = """
                {
                   "KU7K3MBQV9LU8" : {
                     "title" : "Wuthering Heights",
                     "author" : "Emily Brontë",
                     "year" : 1847,
                     "isbn" : "KU7K3MBQV9LU8"
                   },
                   "KU7K3MBQV9LU9" : {
                     "title" : "The Grapes of Wrath",
                     "author" : "John Steinbeck",
                     "year" : 1939,
                     "isbn" : "KU7K3MBQV9LU9"
                   },
                   "KU7K3MBQV9LU6" : {
                     "title" : "Rage",
                     "author" : "Richard Bachman",
                     "year" : 1977,
                     "isbn" : "KU7K3MBQV9LU6"
                   }
                 }
                """;
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(3, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU8"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU9"));
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));

        Book book_1 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU8");
        Book book_2 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU9");
        Book book_3 = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");

        assertBookMatches(book_1, "Wuthering Heights", "Emily Brontë", 1847, "KU7K3MBQV9LU8");
        assertBookMatches(book_2, "The Grapes of Wrath", "John Steinbeck", 1939, "KU7K3MBQV9LU9");
        assertBookMatches(book_3, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("loadFromJsonFile should throw JsonParseException when JSON is invalid")
    @MethodSource("jsonParseExceptionDataProvider")
    public void loadFromJsonFileTest_jsonParseException(String jsonContent, String description) throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonFileJsonParseException.json");
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        IOException exception = assertThrows(IOException.class,
                () -> emptyLibrary.loadFromJsonFile(String.valueOf(tempPath)));

        String expectedMessage = "Invalid JSON syntax in file";
        assertTrue(exception.getMessage().startsWith(expectedMessage));
    }

    static Stream<Arguments> jsonParseExceptionDataProvider() {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "JSON has extra comma"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                
                                }
                                """, "There is one missing bracket in the json"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "There is one missing quotation mark in the json"
                ),
                Arguments.of(
                        """
                                <library>
                                     <book isbn="KU7K3MBQV9LU6">
                                         <title>Rage</title>
                                         <author>Richard Bachman</author>
                                         <year>1977</year>
                                         <isbn>KU7K3MBQV9LU6</isbn>
                                     </book>
                                 </library>
                                """, "There is the xml file, not json"
                )
        );
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("loadFromJsonFile should throw MismatchedInputException when JSON has no required fields, " +
            "extra fields or JSON has different values as key and as isbn")
    @MethodSource("mismatchedInputExceptionDataProvider")
    public void loadFromJsonFileTest_mismatchedInputException(String jsonContent, String description) throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonFileMismatchedInputException.json");
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        IOException exception = assertThrows(IOException.class,
                () -> emptyLibrary.loadFromJsonFile(String.valueOf(tempPath)));

        String expectedMessage = "Invalid JSON syntax in file";
        assertTrue(exception.getMessage().startsWith(expectedMessage));
    }

    static Stream<Arguments> mismatchedInputExceptionDataProvider() {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "JSON has no title field"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "JSON has no author field"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "JSON has no year field"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                  }
                                }
                                """, "JSON has no isbn field"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "title": "The Long walk",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "The Long walk", "JSON has extra title field"
                ), Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "author": "Stephen King",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "Stephen King", "JSON has extra author field"
                ), Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "year": 1978,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                }
                                """, "1978", "JSON has extra year field"
                ), Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                    "isbn": "KU7K3MBQV9LU0",
                                  }
                                }
                                """, "KU7K3MBQV9LU0", "JSON has extra isbn field"
                ),
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU0",
                                  }
                                }
                                """, "KU7K3MBQV9LU0", "JSON has different values as key and as isbn"
                )
        );
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("loadFromJsonFile should throw IOException when JSON has extra field or there is an array in json file")
    @MethodSource("IOExceptionDataProvider")
    public void loadFromJsonFileTest_IOException(String jsonContent, String description) throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonFileIOException.json");
        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        IOException exception = assertThrows(IOException.class,
                () -> emptyLibrary.loadFromJsonFile(String.valueOf(tempPath)));

        String expectedMessage = "Unexpected JSON structure in file";
        assertTrue(exception.getMessage().startsWith(expectedMessage));
    }

    static Stream<Arguments> IOExceptionDataProvider() {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                    "pages": 432
                                  }
                                }
                                """, "JSON has extra field"
                ),
                Arguments.of(
                        """
                                [
                                  "KU7K3MBQV9LU6": {
                                    "title": "Rage",
                                    "author": "Richard Bachman",
                                    "year": 1977,
                                    "isbn": "KU7K3MBQV9LU6",
                                  }
                                ]
                                """, "JSON looks like an array"
                )
        );
    }

    @ParameterizedTest
    @DisplayName("loadFromJson should return a map with books if there are spaces in the beginning or in the end of the file path")
    @CsvSource({
            "' src/test/resources/single-book-library.json', 'One space in the beginning of the file path'",
            "'src/test/resources/single-book-library.json ', 'One space in the end of the file path'",
            "'   src/test/resources/single-book-library.json', 'Few spaces in the beginning of the file path'",
            "'src/test/resources/single-book-library.json   ', 'Few spaces in the end of the file path'",
    })
    public void loadFromJsonSpacesInPathTest(String filePath) throws IOException {
        Path tempPath = tempDir.resolve("loadFromJsonSpacesInPath.json");
        String jsonContent = """
                {
                  "KU7K3MBQV9LU6" : {
                    "title" : "Rage",
                    "author" : "Richard Bachman",
                    "year" : 1977,
                    "isbn" : "KU7K3MBQV9LU6"
                  }
                }
                """;

        Files.writeString(tempPath, jsonContent, StandardCharsets.UTF_8);

        emptyLibrary.loadFromJsonFile(String.valueOf(tempPath));

        assertEquals(1, emptyLibrary.getAllBooks().size());
        assertTrue(emptyLibrary.getAllBooks().containsKey("KU7K3MBQV9LU6"));

        Book book = emptyLibrary.getAllBooks().get("KU7K3MBQV9LU6");

        assertBookMatches(book, "Rage", "Richard Bachman", 1977, "KU7K3MBQV9LU6");
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
