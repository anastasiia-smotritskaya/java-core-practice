package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.TextFileAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TextFileAnalyzerTest {
    final String THREE_LINES_FILE = "src/test/resources/three-lines-file.txt";
    final String EMPTY_FILE = "src/test/resources/empty-file.txt";
    final String EMPTY_BEGINNING_FILE = "src/test/resources/empty-beginning-file.txt";
    final String EMPTY_MIDDLE_FILE = "src/test/resources/empty-middle-file.txt";
    final String EMPTY_END_FILE = "src/test/resources/empty-end-file.txt";

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

    @Test
    void testPortability() throws IOException, URISyntaxException {
        // Вариант 1: ClassLoader
        URL resource = getClass().getClassLoader().getResource("three-lines-file.txt");
        System.out.println("Resource URL: " + resource);

        // Вариант 2: Абсолютный путь через проект
        String projectDir = System.getProperty("user.dir");
        Path manualPath = Paths.get(projectDir, "src", "test", "resources", "three-lines-file.txt");
        System.out.println("Manual path: " + manualPath);

        // Оба должны работать
        int result1 = TextFileAnalyzer.countLines_br(Paths.get(resource.toURI()).toString());
        int result2 = TextFileAnalyzer.countLines_br(manualPath.toString());

        assertEquals(3, result1);
        assertEquals(3, result2);
    }
}
