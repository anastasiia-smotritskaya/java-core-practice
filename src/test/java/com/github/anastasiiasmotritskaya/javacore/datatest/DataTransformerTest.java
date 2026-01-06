package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.DataTransformer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataTransformerTest {
    @TempDir
    Path tempDir;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("filterAndCapitalize should throw IllegalArgumentException when input or output file path is null or empty")
    void filterAndCapitalizeTest_IllegalArgumentException(String filePath) {
        Path existingFile = tempDir.resolve("file-filterAndCapitalizeTest_IllegalArgumentException.txt");

        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> DataTransformer.filterAndCapitalize(filePath, existingFile.toString()));
                    assertEquals("Input file path must not be null or empty.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> DataTransformer.filterAndCapitalize(existingFile.toString(), filePath));
                    assertEquals("Output file path must not be null or empty.", exception.getMessage());
                }
        );
    }

    @Test
    @DisplayName("filterAndCapitalize should throw IOException when input or output file path doesn't exist")
    void filterAndCapitalizeTest_IOException() {
        Path existingFile = tempDir.resolve("file-filterAndCapitalizeTest_IOException.txt");
        assertAll(
                () -> assertThrows(IOException.class, () -> DataTransformer.filterAndCapitalize("/nonexistent/file.txt", existingFile.toString())),
                () -> assertThrows(IOException.class, () -> DataTransformer.filterAndCapitalize(existingFile.toString(), "/nonexistent/file.txt"))
        );
    }

    @Test
    @DisplayName("filterAndCapitalize should return null when output file has 0 bytes")
    void filterAndCapitalize_ZeroBytesInputFileTest() throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalize_ZeroBytesInputFileTest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalize_ZeroBytesInputFileTest.txt");

        Files.write(tempInputFile, new byte[0]);

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        List<String> outputLines = Files.readAllLines(tempOutputFile);
        assertTrue(outputLines.isEmpty());
    }

    @Test
    @DisplayName("filterAndCapitalize should return null when output file has one empty string")
    void filterAndCapitalize_EmptyInputFileTest() throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalize_EmptyInputFileTest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalize_EmptyInputFileTest.txt");

        Files.write(tempInputFile, List.of(""));

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        List<String> outputLines = Files.readAllLines(tempOutputFile);
        assertTrue(outputLines.isEmpty());
    }

    @Test
    @DisplayName("filterAndCapitalize should return null when output file has no strings which start with a or A")
    void filterAndCapitalize_noATest() throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalize_noATest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalize_noATest.txt");

        Files.write(tempInputFile, List.of("""
                the first line has no a or A in the beginning,
                the second line has no a or A in the beginning,
                the third line has no a or A in the beginning
                """));

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        List<String> outputLines = Files.readAllLines(tempOutputFile);
        assertTrue(outputLines.isEmpty());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("filterAndCapitalize positive tests with different arguments")
    @MethodSource("filterAndCapitalizeDataProvider")
    void filterAndCapitalizePositiveTest(String content, String expected, String description) throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalizePositiveTest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalizePositiveTest.txt");

        Files.write(tempInputFile, List.of(content));

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        String currentLine;
        StringBuilder actual = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutputFile.toString(), StandardCharsets.UTF_8))) {
            while ((currentLine = reader.readLine()) != null) {
                actual.append(currentLine).append("\n");
            }
        }
        assertEquals(expected, actual.toString());
    }

    static Stream<Arguments> filterAndCapitalizeDataProvider() {
        return Stream.of(
                Arguments.of("""
                                A the first line has A,
                                B the second line has B,
                                C the third line has C
                                """,
                        "A THE FIRST LINE HAS A,\n",
                        "Text has A strings in the beginning"),
                Arguments.of("""
                                a the first line has a,
                                b the second line has b,
                                c the third line has c
                                """,
                        "A THE FIRST LINE HAS A,\n",
                        "Text has a strings in the beginning"),
                Arguments.of("""
                                c the first line has c,
                                a the second line has a,
                                b the third line has b,
                                A the fourth line has A
                                """,
                        """
                                A THE SECOND LINE HAS A,
                                A THE FOURTH LINE HAS A
                                """,
                        "Text has A and a strings in the middle"),
                Arguments.of("""
                                A
                                B the second line
                                C the third line
                                a
                                b the fifth line
                                c the sixth line
                                """,
                        """
                                A
                                A
                                """,
                        "Text has a or A strings with no other words"),
                Arguments.of("""
                                A1111111111
                                B2222222222
                                C3333333333
                                a4444444444
                                b5555555555
                                c6666666666
                                """,
                        """
                                A1111111111
                                A4444444444
                                """,
                        "Text has numbers"),
                Arguments.of("""
                                A!!!!!!!!!!
                                B@@@@@@@@@@
                                C##########
                                a$$$$$$$$$$
                                b%%%%%%%%%%
                                c^^^^^^^^^^
                                """,
                        """
                                A!!!!!!!!!!
                                A$$$$$$$$$$
                                """,
                        "Text has special characters")
        );
    }

    @Test
    @DisplayName("filterAndCapitalize should overwrite the information in the output file if there is already text there")
    void filterAndCapitalize_outputFileAlreadyHasTextTest() throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalize_outputFileAlreadyHasTextTest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalize_outputFileAlreadyHasTextTest.txt");

        Files.write(tempInputFile, List.of("A in the input file"));
        Files.write(tempOutputFile, List.of("B in the output file"));

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        String currentLine;
        StringBuilder actual = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutputFile.toString(), StandardCharsets.UTF_8))) {
            while ((currentLine = reader.readLine()) != null) {
                actual.append(currentLine).append("\n");
            }
        }

        String expected = "A IN THE INPUT FILE\n";

        assertEquals(expected, actual.toString());
    }

    @Test
    @DisplayName("filterAndCapitalize should ignore lines starting with space")
    void filterAndCapitalize_spaceInTheBeginningTest() throws IOException {
        Path tempInputFile = tempDir.resolve("temp-input-file-filterAndCapitalize_spaceInTheBeginningTest.txt");
        Path tempOutputFile = tempDir.resolve("temp-output-file-filterAndCapitalize_spaceInTheBeginningTest.txt");

        Files.write(tempInputFile, List.of(" A the first line has space in the beginning", "a the second line has no space in the beginning"));

        DataTransformer.filterAndCapitalize(tempInputFile.toString(), tempOutputFile.toString());

        String currentLine;
        StringBuilder actual = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutputFile.toString(), StandardCharsets.UTF_8))) {
            while ((currentLine = reader.readLine()) != null) {
                actual.append(currentLine).append("\n");
            }
        }

        String expected = "A THE SECOND LINE HAS NO SPACE IN THE BEGINNING\n";

        assertEquals(expected, actual.toString());
    }
}
