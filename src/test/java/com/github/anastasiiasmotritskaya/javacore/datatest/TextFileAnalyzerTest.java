package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.TextFileAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextFileAnalyzerTest {
    final String THREE_LINES_FILE = "src\\test-files\\three-line-file.txt";
    final String EMPTY_FILE = "src\\test-files\\empty-file.txt";
    final String EMPTY_BEGINNING_FILE = "src\\test-files\\empty-beginning-file.txt";
    final String EMPTY_MIDDLE_FILE = "src\\test-files\\empty-middle-file.txt";
    final String EMPTY_END_FILE = "src\\test-files\\empty-end-file.txt";

    @Test
    @DisplayName("countLines should count lines in file with text")
    void countLinesTest() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(THREE_LINES_FILE));
    }

    @Test
    @DisplayName("countLines should count 0 lines in the file without text")
    void countLinesEmptyFileTest() throws IOException {
        assertEquals(0, TextFileAnalyzer.countLines_br(EMPTY_FILE));
    }

    @Test
    @DisplayName("countLines should count empty lines in the beginning of the file")
    void countLinesEmptyBeginningFileTest() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(EMPTY_BEGINNING_FILE));
    }

    @Test
    @DisplayName("countLines should count empty lines in the middle of the file")
    void countLinesEmptyMiddleFileTest() throws IOException {
        assertEquals(3, TextFileAnalyzer.countLines_br(EMPTY_MIDDLE_FILE));
    }

    @Test
    @DisplayName("countLines doesn't count empty line in the end of the file")
    void countLinesEmptyEndFileTest() throws IOException {
        assertEquals(2, TextFileAnalyzer.countLines_br(EMPTY_END_FILE));
    }

    @Test
    @DisplayName("countLines should throw IOException, when file doesn't exist")
    void countLinesTest_IOException() {
        assertThrows(IOException.class, () -> TextFileAnalyzer.countLines_br("C:\\Users\\not-exist-file.txt"));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("countLines should throw IllegalArgumentException when the file path is null or empty")
    void countLinesTest_IllegalArgumentException(String filePath) {
        assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.countLines_br(filePath));
    }
}
