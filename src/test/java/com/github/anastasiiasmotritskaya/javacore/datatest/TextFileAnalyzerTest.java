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

    @TempDir
    Path tempDir;

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
    @DisplayName("countLines_br should count 0 lines in the file without text")
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
                () -> {
                    Exception exception_countLines_br = assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.countLines_br(filePath));
                    assertEquals("File path must not be null or empty.", exception_countLines_br.getMessage());
                },
                () -> {
                    Exception exception_countLines_files = assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.countLines_files(filePath));
                    assertEquals("File path must not be null or empty.", exception_countLines_files.getMessage());
                }
        );
    }

    @Test
    @DisplayName("countLines_files should count lines in file with text")
    void countLines_files_Test() throws IOException {
        Path threeLinesFile = Files.createTempFile("three-lines-file-countLines", ".txt");
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
        Path zeroBytesFile = Files.createTempFile("zero-bytes-file-countLines", ".txt");
        Files.write(zeroBytesFile, new byte[0]);
        try {
            assertEquals(0, TextFileAnalyzer.countLines_files(zeroBytesFile.toString()));
        } finally {
            Files.deleteIfExists(zeroBytesFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count 1 line in the file with one empty line")
    void countLines_files_oneEmptyLineTest() throws IOException {
        Path emptyLineFile = Files.createTempFile("empty-line-file-countLines", ".txt");
        Files.write(emptyLineFile, List.of(""));
        try {
            assertEquals(1, TextFileAnalyzer.countLines_files(emptyLineFile.toString()));
        } finally {
            Files.deleteIfExists(emptyLineFile);
        }
    }

    @Test
    @DisplayName("countLines_files should count empty lines in the beginning of the file")
    void countLines_files_EmptyBeginningFileTest() throws IOException {
        Path emptyBeginningFile = Files.createTempFile("empty-beginning-file-countLines", ".txt");
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
        Path emptyMiddleFile = Files.createTempFile("empty-middle-file-countLines", ".txt");
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
        Path emptyEndFile = Files.createTempFile("empty-end-file-countLines", ".txt");
        Files.write(emptyEndFile, Arrays.asList("line 1", "line 2", ""));
        try {
            assertEquals(3, TextFileAnalyzer.countLines_files(emptyEndFile.toString()));
        } finally {
            Files.deleteIfExists(emptyEndFile);
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("findLongestWord should throw IllegalArgumentException when the file path is null or empty")
    void findLongestWordTest_IllegalArgumentException(String filePath) {
        assertAll(
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.findLongestWord_br(filePath));
                    assertEquals("File path must not be null or empty.", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> TextFileAnalyzer.findLongestWord_files(filePath));
                    assertEquals("File path must not be null or empty.", exception.getMessage());
                }
        );
    }

    @Test
    @DisplayName("findLongestWord should throw IOException, when file doesn't exist")
    void findLongestWordTest_IOException() {
        assertAll(
                () -> assertThrows(IOException.class, () -> TextFileAnalyzer.findLongestWord_br("/nonexistent/file.txt")),
                () -> assertThrows(IOException.class, () -> TextFileAnalyzer.findLongestWord_files("/nonexistent/file.txt"))
        );
    }

    @Test
    @DisplayName("findLongestWord_br should return empty string when it reads from the file without any text (0 byte)")
    void findLongestWord_br_EmptyFileTest() throws IOException {
        Path zeroBytesFile = Files.createTempFile("zero-bytes-file-findLongestWord_br", ".txt");
        Files.write(zeroBytesFile, new byte[0]);
        try {
            assertEquals("", TextFileAnalyzer.findLongestWord_br(zeroBytesFile.toString()));
        } finally {
            Files.deleteIfExists(zeroBytesFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should return empty string when it reads from the file without any text (0 byte)")
    void findLongestWord_files_EmptyFileTest() throws IOException {
        Path zeroBytesFile = tempDir.resolve("zero-bytes-file-findLongestWord_files.txt");
        Files.write(zeroBytesFile, new byte[0]);
        assertEquals("", TextFileAnalyzer.findLongestWord_files(zeroBytesFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should return empty string when it reads from the file with one empty line")
    void findLongestWord_br_oneEmptyLineTest() throws IOException {
        Path emptyLineFile = Files.createTempFile("empty-line-file-findLongestWord_br", ".txt");
        Files.write(emptyLineFile, List.of(""));
        try {
            assertEquals("", TextFileAnalyzer.findLongestWord_br(emptyLineFile.toString()));
        } finally {
            Files.deleteIfExists(emptyLineFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should return empty string when it reads from the file with one empty line")
    void findLongestWord_files_oneEmptyLineTest() throws IOException {
        Path emptyLineFile = tempDir.resolve("empty-line-file-findLongestWord_files.txt");
        Files.write(emptyLineFile, List.of(""));
        assertEquals("", TextFileAnalyzer.findLongestWord_files(emptyLineFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should return empty string for file with only numbers")
    void findLongestWord_br_onlyNumbersTest() throws IOException {
        Path onlyNumbersFile = Files.createTempFile("only-numbers-file-findLongestWord_br", ".txt");
        Files.write(onlyNumbersFile, List.of("1 22 333 4444 333 22 1"));
        try {
            assertEquals("", TextFileAnalyzer.findLongestWord_br(onlyNumbersFile.toString()));
        } finally {
            Files.deleteIfExists(onlyNumbersFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should return empty string for file with only numbers")
    void findLongestWord_files_onlyNumbersTest() throws IOException {
        Path onlyNumbersFile = tempDir.resolve("only-number-file-findLongestWord_files.txt");
        Files.write(onlyNumbersFile, List.of("1 22 333 4444 333 22 1"));
        assertEquals("", TextFileAnalyzer.findLongestWord_files(onlyNumbersFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should return empty string when it reads from the file only with special characters")
    void findLongestWord_br_onlySpecialCharactersTest() throws IOException {
        Path onlySpecialCharactersFile = Files.createTempFile("only-special-characters-file-findLongestWord_br", ".txt");
        Files.write(onlySpecialCharactersFile, List.of("! @@ #### $$$$$ #### @@ !!"));
        try {
            assertEquals("", TextFileAnalyzer.findLongestWord_br(onlySpecialCharactersFile.toString()));
        } finally {
            Files.deleteIfExists(onlySpecialCharactersFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should return empty string when it reads from the file only with special characters")
    void findLongestWord_files_onlySpecialCharactersTest() throws IOException {
        Path onlySpecialCharactersFile = tempDir.resolve("only-special-character-file-findLongestWord_files.txt");
        Files.write(onlySpecialCharactersFile, List.of("! @@ #### $$$$$ #### @@ !!"));
        assertEquals("", TextFileAnalyzer.findLongestWord_files(onlySpecialCharactersFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should fins the longest word in text with three lines")
    void findLongestWord_br_ThreeLinesTest() throws IOException {
        Path threeLinesFile = Files.createTempFile("three-lines-file-findLongestWord_br", ".txt");
        Files.write(threeLinesFile, Arrays.asList("cat", "chicken", "lama"));
        try {
            assertEquals("chicken", TextFileAnalyzer.findLongestWord_br(threeLinesFile.toString()));
        } finally {
            Files.deleteIfExists(threeLinesFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should fins the longest word in text with three lines")
    void findLongestWord_files_ThreeLinesTest() throws IOException {
        Path threeLinesFile = tempDir.resolve("three-lines-file-findLongestWord_files.txt");
        Files.write(threeLinesFile, Arrays.asList("cat", "chicken", "lama"));
        assertEquals("chicken", TextFileAnalyzer.findLongestWord_files(threeLinesFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word in text with one line")
    void findLongestWord_br_oneLineTest() throws IOException {
        Path oneLineFile = Files.createTempFile("one-line-file-findLongestWord_br", ".txt");
        Files.write(oneLineFile, List.of("cat chicken lama"));
        try {
            assertEquals("chicken", TextFileAnalyzer.findLongestWord_br(oneLineFile.toString()));
        } finally {
            Files.deleteIfExists(oneLineFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word in text with one line")
    void findLongestWord_files_oneLineTest() throws IOException {
        Path oneLineFile = tempDir.resolve("one-line-file-findLongestWord_files.txt");
        Files.write(oneLineFile, List.of("cat chicken lama"));
        assertEquals("chicken", TextFileAnalyzer.findLongestWord_files(oneLineFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word in text with words and numbers (word is longer)")
    void findLongestWord_br_textWithNumbers_wordIsLonger_Test() throws IOException {
        Path wordIsLongerFile = Files.createTempFile("word-is-longer-file-findLongestWord_br", ".txt");
        Files.write(wordIsLongerFile, List.of("123 chicken 4567"));
        try {
            assertEquals("chicken", TextFileAnalyzer.findLongestWord_br(wordIsLongerFile.toString()));
        } finally {
            Files.deleteIfExists(wordIsLongerFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word in text with words and numbers (word is longer)")
    void findLongestWord_files_textWithNumbers_wordIsLonger_Test() throws IOException {
        Path wordIsLongerFile = tempDir.resolve("words-is-longer-file-findLongestWord_files.txt");
        Files.write(wordIsLongerFile, List.of("123 chicken 4567"));
        assertEquals("chicken", TextFileAnalyzer.findLongestWord_files(wordIsLongerFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word in text with words and numbers (number is longer)")
    void findLongestWord_br_textWithNumbers_numberIsLonger_Test() throws IOException {
        Path numberIsLongerFile = Files.createTempFile("number-is-longer-file-findLongestWord_br", ".txt");
        Files.write(numberIsLongerFile, List.of("cat 1234567 lama"));
        try {
            assertEquals("lama", TextFileAnalyzer.findLongestWord_br(numberIsLongerFile.toString()));
        } finally {
            Files.deleteIfExists(numberIsLongerFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word in text with words and numbers (number is longer)")
    void findLongestWord_files_textWithNumbers_numberIsLonger_Test() throws IOException {
        Path numberIsLongerFile = tempDir.resolve("number-is-longer-file-findLongestWord_files.txt");
        Files.write(numberIsLongerFile, List.of("cat 1234567 lama"));
        assertEquals("lama", TextFileAnalyzer.findLongestWord_files(numberIsLongerFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word in text with words and characters")
    void findLongestWord_br_textWithCharacters_Test() throws IOException {
        Path textWithCharactersFile = Files.createTempFile("word-longest-file-findLongestWord_br", ".txt");
        Files.write(textWithCharactersFile, List.of("@@@ chicken $$$$$$$$$$$"));
        try {
            assertEquals("chicken", TextFileAnalyzer.findLongestWord_br(textWithCharactersFile.toString()));
        } finally {
            Files.deleteIfExists(textWithCharactersFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word in text with words and characters")
    void findLongestWord_files_textWithCharacters_Test() throws IOException {
        Path textWithCharactersFile = tempDir.resolve("text-with-characters-file-findLongestWord_files.txt");
        Files.write(textWithCharactersFile, List.of("@@@ chicken $$$$$$$$$$$"));
        assertEquals("chicken", TextFileAnalyzer.findLongestWord_files(textWithCharactersFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word when it has hyphen")
    void findLongestWord_br_wordWithHyphen_Test() throws IOException {
        Path wordWithHyphenFile = Files.createTempFile("hyphen-file-findLongestWord_br", ".txt");
        Files.write(wordWithHyphenFile, List.of("mom mother-in-law dad granny"));
        try {
            assertEquals("mother-in-law", TextFileAnalyzer.findLongestWord_br(wordWithHyphenFile.toString()));
        } finally {
            Files.deleteIfExists(wordWithHyphenFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word when it has hyphen")
    void findLongestWord_files_wordWithHyphen_Test() throws IOException {
        Path wordWithHyphenFile = tempDir.resolve("word-with-hyphen-file.txt");
        Files.write(wordWithHyphenFile, List.of("mom mother-in-law dad granny"));
        assertEquals("mother-in-law", TextFileAnalyzer.findLongestWord_files(wordWithHyphenFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word when it has apostrophe")
    void findLongestWord_br_wordWithApostrophe_Test() throws IOException {
        Path wordWithApostropheFile = Files.createTempFile("apostrophe-file-findLongestWord_br", ".txt");
        Files.write(wordWithApostropheFile, List.of("Smith O'Connor Potter"));
        try {
            assertEquals("O'Connor", TextFileAnalyzer.findLongestWord_br(wordWithApostropheFile.toString()));
        } finally {
            Files.deleteIfExists(wordWithApostropheFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word when it has apostrophe")
    void findLongestWord_files_wordWithApostrophe_Test() throws IOException {
        Path wordWithApostropheFile = tempDir.resolve("word-with-apostrophe-file-findLongestWord_files.txt");
        Files.write(wordWithApostropheFile, List.of("Smith O'Connor Potter"));
        assertEquals("O'Connor", TextFileAnalyzer.findLongestWord_files(wordWithApostropheFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should find the longest word when it consist only of one letter")
    void findLongestWord_br_oneLetter_Test() throws IOException {
        Path oneLetterFile = Files.createTempFile("one-letter-file-findLongestWord_br", ".txt");
        Files.write(oneLetterFile, List.of("a"));
        try {
            assertEquals("a", TextFileAnalyzer.findLongestWord_br(oneLetterFile.toString()));
        } finally {
            Files.deleteIfExists(oneLetterFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should find the longest word when it consist only of one letter")
    void findLongestWord_files_oneLetter_Test() throws IOException {
        Path oneLetterFile = tempDir.resolve("one-letter-file-findLongestWord_files.txt");
        Files.write(oneLetterFile, List.of("a"));
        assertEquals("a", TextFileAnalyzer.findLongestWord_files(oneLetterFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br should return first longest word when multiple words have same length")
    void findLongestWord_br_sameLengthWords_Test() throws IOException {
        Path sameLengthWordsFile = Files.createTempFile("same-length-words-findLongestWord_br", ".txt");
        Files.write(sameLengthWordsFile, List.of("cat dog elephant giraffe"));
        try {
            assertEquals("elephant", TextFileAnalyzer.findLongestWord_br(sameLengthWordsFile.toString()));
        } finally {
            Files.deleteIfExists(sameLengthWordsFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files should return first longest word when multiple words have same length")
    void findLongestWord_files_sameLengthWords_Test() throws IOException {
        Path sameLengthWordsFile = tempDir.resolve("same-length-words-file-findLongestWord_files.txt");
        Files.write(sameLengthWordsFile, List.of("cat dog elephant giraffe"));
        assertEquals("elephant", TextFileAnalyzer.findLongestWord_files(sameLengthWordsFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have a hyphen at the beginning")
    void findLongestWord_br_hyphenInTheBeginning_Test() throws IOException {
        Path hyphenInTheBeginningFile = Files.createTempFile("hyphen-in-the-beginning-file-findLongestWord_br", ".txt");
        Files.write(hyphenInTheBeginningFile, List.of("cat -dog pig"));
        try {
            assertEquals("-dog", TextFileAnalyzer.findLongestWord_br(hyphenInTheBeginningFile.toString()));
        } finally {
            Files.deleteIfExists(hyphenInTheBeginningFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have a hyphen at the beginning")
    void findLongestWord_files_hyphenInTheBeginning_Test() throws IOException {
        Path hyphenInTheBeginningFile = tempDir.resolve("hyphen-int-the-beginning-file-findLongestWord_files.txt");
        Files.write(hyphenInTheBeginningFile, List.of("cat -dog pig"));
        assertEquals("-dog", TextFileAnalyzer.findLongestWord_files(hyphenInTheBeginningFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have a hyphen at the end")
    void findLongestWord_br_hyphenInEnd_Test() throws IOException {
        Path hyphenInTheEndFile = Files.createTempFile("hyphen-in-the-end-file-findLongestWord_br", ".txt");
        Files.write(hyphenInTheEndFile, List.of("cat dog- pig"));
        try {
            assertEquals("dog-", TextFileAnalyzer.findLongestWord_br(hyphenInTheEndFile.toString()));
        } finally {
            Files.deleteIfExists(hyphenInTheEndFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have a hyphen at the end")
    void findLongestWord_files_hyphenInEnd_Test() throws IOException {
        Path hyphenInTheEndFile = tempDir.resolve("hyphen-in-the-end-file-findLongestWord_files.txt");
        Files.write(hyphenInTheEndFile, List.of("cat dog- pig"));
        assertEquals("dog-", TextFileAnalyzer.findLongestWord_files(hyphenInTheEndFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have two hyphens")
    void findLongestWord_br_twoHyphens_Test() throws IOException {
        Path twoHyphensFile = Files.createTempFile("two-hyphens-file-findLongestWord_br", ".txt");
        Files.write(twoHyphensFile, List.of("doggy-dog kitty--cat"));
        try {
            assertEquals("kitty--cat", TextFileAnalyzer.findLongestWord_br(twoHyphensFile.toString()));
        } finally {
            Files.deleteIfExists(twoHyphensFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have two hyphens")
    void findLongestWord_files_twoHyphens_Test() throws IOException {
        Path twoHyphensFile = tempDir.resolve("two-hyphens-file-findLongestWord_files.txt");
        Files.write(twoHyphensFile, List.of("doggy-dog kitty--cat"));
        assertEquals("kitty--cat", TextFileAnalyzer.findLongestWord_files(twoHyphensFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have an apostrophe at the beginning")
    void findLongestWord_br_apostropheInTheBeginning_Test() throws IOException {
        Path apostropheInTheBeginningFile = Files.createTempFile("apostrophe-in-the-beginning-file-findLongestWord_br", ".txt");
        Files.write(apostropheInTheBeginningFile, List.of("cat 'dog pig"));
        try {
            assertEquals("'dog", TextFileAnalyzer.findLongestWord_br(apostropheInTheBeginningFile.toString()));
        } finally {
            Files.deleteIfExists(apostropheInTheBeginningFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have an apostrophe at the beginning")
    void findLongestWord_files_apostropheInTheBeginning_Test() throws IOException {
        Path apostropheInTheBeginningFile = tempDir.resolve("apostrophe-in-the-beginning-file-findLongestWord_files.txt");
        Files.write(apostropheInTheBeginningFile, List.of("cat 'dog pig"));
        assertEquals("'dog", TextFileAnalyzer.findLongestWord_files(apostropheInTheBeginningFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have an apostrophe at the end")
    void findLongestWord_br_apostropheInTheEnd_Test() throws IOException {
        Path apostropheInTheEndFile = Files.createTempFile("apostrophe-in-the-end-file-findLongestWord_br", ".txt");
        Files.write(apostropheInTheEndFile, List.of("cat dog' pig"));
        try {
            assertEquals("dog'", TextFileAnalyzer.findLongestWord_br(apostropheInTheEndFile.toString()));
        } finally {
            Files.deleteIfExists(apostropheInTheEndFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have an apostrophe at the end")
    void findLongestWord_files_apostropheInTheEnd_Test() throws IOException {
        Path apostropheInTheEndFile = tempDir.resolve("apostrophe-in-the-end-file-findLongestWord_files.txt");
        Files.write(apostropheInTheEndFile, List.of("cat dog' pig"));
        assertEquals("dog'", TextFileAnalyzer.findLongestWord_files(apostropheInTheEndFile.toString()));
    }

    @Test
    @DisplayName("findLongestWord_br considers that a word can have two apostrophes")
    void findLongestWord_br_twoApostrophes_Test() throws IOException {
        Path twoApostrophesFile = Files.createTempFile("two-apostrophes-file-findLongestWord_br", ".txt");
        Files.write(twoApostrophesFile, List.of("doggy'dog kitty''cat"));
        try {
            assertEquals("kitty''cat", TextFileAnalyzer.findLongestWord_br(twoApostrophesFile.toString()));
        } finally {
            Files.deleteIfExists(twoApostrophesFile);
        }
    }

    @Test
    @DisplayName("findLongestWord_files considers that a word can have two apostrophes")
    void findLongestWord_files_twoApostrophes_Test() throws IOException {
        Path twoApostrophesFile = tempDir.resolve("two-apostrophes-file-findLongestWord_files.txt");
        Files.write(twoApostrophesFile, List.of("doggy'dog kitty''cat"));
        assertEquals("kitty''cat", TextFileAnalyzer.findLongestWord_files(twoApostrophesFile.toString()));
    }
}
