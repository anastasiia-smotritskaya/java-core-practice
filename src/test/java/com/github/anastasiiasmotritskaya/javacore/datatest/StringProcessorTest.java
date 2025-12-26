package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.StringProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    @Test
    @DisplayName("isPalindrome and isPalindrome_char simple positive test")
    void isPalindromeWithPalindromeStringTest() {
        assertAll(
                () -> assertTrue(StringProcessor.isPalindrome("madam")),
                () -> assertTrue(StringProcessor.isPalindrome_char("madam"))
        );
    }

    @Test
    @DisplayName("isPalindrome and isPalindrome_char simple positive test with upper case")
    void isPalindromeUpperCaseTest() {
        assertAll(
                () -> assertTrue(StringProcessor.isPalindrome("Madam")),
                () -> assertTrue(StringProcessor.isPalindrome_char("Madam"))
        );
    }

    @Test
    @DisplayName("isPalindrome and isPalindrome_char simple negative test")
    void isPalindromeWithNonPalindromeStringTest() {
        assertAll(
                () -> assertFalse(StringProcessor.isPalindrome("hello")),
                () -> assertFalse(StringProcessor.isPalindrome_char("hello"))
        );
    }

    @Test
    @DisplayName("isPalindrome and isPalindrome_char should work properly with short strings")
    void isPalindromeWithSingleCharacterTest() {
        assertAll(
                () -> assertTrue(StringProcessor.isPalindrome("a")),
                () -> assertTrue(StringProcessor.isPalindrome_char("a"))
        );
    }

    @Test
    @DisplayName("isPalindrome and isPalindrome_char should throw IllegalArgumentException, if the string is null")
    void isPalindromeWithNoCharacterTest() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> StringProcessor.isPalindrome(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                },

                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> StringProcessor.isPalindrome_char(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                }
        );
    }


    @ParameterizedTest(name = "{index}: {1}")
    @DisplayName("isPalindrome and isPalindrome_char should work properly with strings with spaces")
    @CsvSource({
            "'A man a plan a canal Panama   ', 'Spaces in the end of the input string'",
            "'   A man a plan a canal Panama', 'Spaces in the beginning of the input string'",
            "'race car', 'Spaces in the middle of the input string'",
            "'', 'Empty string'"
    })
    void isPalindromeSpacesTest(String original, String description) {
        assertAll(
                () -> assertTrue(StringProcessor.isPalindrome(original)),
                () -> assertTrue(StringProcessor.isPalindrome_char(original))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char simple positive test")
    void reverseStringPositiveTest() {
        assertAll(
                () -> assertEquals("tseT", StringProcessor.reverseString("Test")),
                () -> assertEquals("tseT", StringProcessor.reverseString_char("Test"))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char should throw IllegalArgumentException for null string")
    void reverseStringEmptyTest() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> StringProcessor.reverseString(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> StringProcessor.reverseString_char(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest(name = "{index}: {2}")
    @DisplayName("reverseString and reverseString_char should work properly with strings with spaces")
    @CsvSource({
            "' tseT', 'Test ', 'Spaces in the end of the input string'",
            "'tseT ', ' Test', 'Spaces in the beginning of the input string'",
            "'ts   eT', 'Te   st', 'Spaces in the middle of the input string'"
    }

    )
    void reverseStringSpacesTest(String expected, String input, String description) {
        assertAll(
                () -> assertEquals(expected, StringProcessor.reverseString(input)),
                () -> assertEquals(expected, StringProcessor.reverseString_char(input))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char should work properly with short strings")
    void reverseStringShortStringTest() {
        assertAll(
                () -> assertEquals("F", StringProcessor.reverseString("F")),
                () -> assertEquals("F", StringProcessor.reverseString_char("F"))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char should work properly with empty strings")
    void reverseStringEmptyStringTest() {
        assertAll(
                () -> assertEquals("", StringProcessor.reverseString("")),
                () -> assertEquals("", StringProcessor.reverseString_char(""))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char should work properly with numbers")
    void reverseStringWithNumbersTest() {
        assertAll(
                () -> assertEquals("321", StringProcessor.reverseString("123")),
                () -> assertEquals("321", StringProcessor.reverseString_char("123"))
        );
    }

    @Test
    @DisplayName("reverseString and reverseString_char should work properly with special characters")
    void reverseStringWithSpecialCharsTest() {
        assertAll(
                () -> assertEquals("!@#$", StringProcessor.reverseString("$#@!")),
                () -> assertEquals("!@#$", StringProcessor.reverseString_char("$#@!"))
        );
    }

    @Test
    void countVowelsPositiveTest() {
        assertAll(
                () -> assertEquals(2, StringProcessor.countVowels("camel")),
                () -> assertEquals(0, StringProcessor.countVowels("123")),
                () -> assertEquals(0, StringProcessor.countVowels("0")),
                () -> assertEquals(0, StringProcessor.countVowels(":*?)№*"))
        );
    }

    @Test
    void countVowelsNullStringTest() {
        assertEquals(0, StringProcessor.countVowels(null));
    }

    @Test
    void countVowelsEmptyStringTest() {
        assertEquals(0, StringProcessor.countVowels(""));
    }

    @Test
    void countVowelsDifferentCasesTest() {
        assertEquals(8, StringProcessor.countVowels("A camel is an animal"));
    }

    @Test
    void countVowelsMixedLanguagesTest() {
        assertEquals(4, StringProcessor.countVowels("Привет Hello"));
    }

    @Test
    void removeSpacesTest() {
        assertAll("removeSpaces should remove all spaces",
                () -> assertEquals("camel", StringProcessor.removeSpaces("cam el")),
                () -> assertEquals("camel", StringProcessor.removeSpaces(" camel")),
                () -> assertEquals("camel", StringProcessor.removeSpaces("camel ")),
                () -> assertEquals("camel", StringProcessor.removeSpaces("c a m e l")),
                () -> assertEquals("", StringProcessor.removeSpaces("")),
                () -> assertEquals("", StringProcessor.removeSpaces("    "))
        );
    }

    @Test
    void removeSpacesNullTest() {
        assertNull(StringProcessor.removeSpaces(null));
    }

    @Test
    void capitalizeWordsTest() {
        assertAll(
                () -> assertEquals("The Camel Is Great", StringProcessor.capitalizeWords("the camel is great")),
                () -> assertEquals("The Camel Is Great", StringProcessor.capitalizeWords("THE CAMEL IS GREAT")),
                () -> assertEquals("", StringProcessor.capitalizeWords("")),
                () -> assertEquals("", StringProcessor.capitalizeWords("   ")),
                () -> assertEquals("One Two", StringProcessor.capitalizeWords("one   two")),
                () -> assertEquals("123", StringProcessor.capitalizeWords("123")),
                () -> assertEquals("@#$%", StringProcessor.capitalizeWords("@#$%"))
        );
    }

    @Test
    void capitalizeWordsNull() {
        assertNull(StringProcessor.capitalizeWords(null));
    }
}
