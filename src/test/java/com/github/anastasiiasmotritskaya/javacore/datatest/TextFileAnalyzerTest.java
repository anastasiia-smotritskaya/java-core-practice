package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.TextFileAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextFileAnalyzerTest {
    final String THREE_LINES_FILE = "src/test/resources/three-lines-file.txt";
    final String EMPTY_FILE = "src/test/resources/empty-file.txt";
    final String EMPTY_BEGINNING_FILE = "src/test/resources/empty-beginning-file.txt";
    final String EMPTY_MIDDLE_FILE = "src/test/resources/empty-middle-file.txt";
    final String EMPTY_END_FILE = "src/test/resources/empty-end-file.txt";

    @Test
    @DisplayName("countLines_br should count lines in file with text")
    void countLines_br_Test() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(THREE_LINES_FILE));
    }

    @Test
    @DisplayName("countLines_br  should count 0 lines in the file without text")
    void countLines_br_EmptyFileTest() throws IOException {
        assertEquals(0, TextFileAnalyzer.countLines_br(EMPTY_FILE));
    }

    @Test
    @DisplayName("countLines_br should count empty lines in the beginning of the file")
    void countLines_br_EmptyBeginningFileTest() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(EMPTY_BEGINNING_FILE));
    }

    @Test
    @DisplayName("countLines_br should count empty lines in the middle of the file")
    void countLines_br_EmptyMiddleFileTest() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(EMPTY_MIDDLE_FILE));
    }

    @Test
    @DisplayName("countLines_br doesn't count empty line in the end of the file")
    void countLines_br_EmptyEndFileTest() throws IOException {
        assertEquals(2, TextFileAnalyzer.countLines_br(EMPTY_END_FILE));
    }

    @Test
    @DisplayName("countLines should throw IOException, when file doesn't exist")
    void countLinesTest_IOException() {
        assertAll(
                () -> assertThrows(IOException.class, () -> TextFileAnalyzer.countLines_br("/nonexistent/file.txt")),
                () -> assertThrows(IOException.class, () -> TextFileAnalyzer.countLines_files("/nonexistent/file.txt"))
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("countLines should throw IllegalArgumentException when the file path is null or empty")
    void countLinesTest_IllegalArgumentException(String filePath) {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.countLines_br(filePath)),
                () -> assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.countLines_files(filePath))
        );
    }

    @Test
    @DisplayName("countLines_files should count lines in file with text")
    void countLines_files_Test() throws IOException {
        Path threeLinesFile = Files.createTempFile("three-lines-file", ".txt");
        Files.write(threeLinesFile, Arrays.asList("line 1", "line 2", "line 3"));
        try {
            assertEquals(3, TextFileAnalyzer.countLines_files(threeLinesFile.toString()));
        } finally {
            Files.deleteIfExists(threeLinesFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count 0 line in the file without any text")
    void countLines_files_EmptyFileTest() throws IOException {
        Path emptyFile = Files.createTempFile("empty-file", ".txt");
        Files.write(emptyFile, new byte[0]);
        try {
            assertEquals(0, TextFileAnalyzer.countLines_files(emptyFile.toString()));
        } finally {
            Files.deleteIfExists(emptyFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count 1 line in the file with one empty line")
    void countLines_files_oneEmptyLineTest() throws IOException {
        Path emptyFile = Files.createTempFile("empty-file", ".txt");
        Files.write(emptyFile, List.of(""));
        try {
            assertEquals(1, TextFileAnalyzer.countLines_files(emptyFile.toString()));
        } finally {
            Files.deleteIfExists(emptyFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count empty lines in the beginning of the file")
    void countLines_files_EmptyBeginningFileTest() throws IOException {
        Path emptyBeginningFile = Files.createTempFile("empty-beginning-file", ".txt");
        Files.write(emptyBeginningFile, Arrays.asList("", "line 2", "line 3"));
        try {
            assertEquals(3, TextFileAnalyzer.countLines_files(emptyBeginningFile.toString()));
        } finally {
            Files.deleteIfExists(emptyBeginningFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count empty lines in the middle of the file")
    void countLines_files_EmptyMiddleFileTest() throws IOException {
        Path emptyMiddleFile = Files.createTempFile("empty-middle-file", ".txt");
        Files.write(emptyMiddleFile, Arrays.asList("line 1", "", "line 3"));
        try {
            assertEquals(3, TextFileAnalyzer.countLines_files(emptyMiddleFile.toString()));
        } finally {
            Files.deleteIfExists(emptyMiddleFile);
        }
    }

    @Test
    @DisplayName("countLines_files сounts empty line in the end of the file")
    void countLines_files_EmptyEndFileTest() throws IOException {
        Path emptyEndFile = Files.createTempFile("empty-end-file", ".txt");
        Files.write(emptyEndFile, Arrays.asList("line 1", "line 2", ""));
        try {
            assertEquals(3, TextFileAnalyzer.countLines_files(emptyEndFile.toString()));
        } finally {
            Files.deleteIfExists(emptyEndFile);
        }
    }
}
