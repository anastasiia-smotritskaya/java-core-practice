package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.StringProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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


    @ParameterizedTest(name = "[{index}]: {1}")
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

    @ParameterizedTest(name = "[{index}]: {2}")
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
    void capitalizeWordsNullTest() {
        assertNull(StringProcessor.capitalizeWords(null));
    }

    @Test
    @DisplayName("countCharacters should throws IllegalArgumentException, when the string is null")
    void countCharactersTest_nullString_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.countCharacters(null));
        assertEquals("Input string cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("countCharacters should return empty map, when the string is empty")
    void countCharactersTest_emptyString_throwsIllegalArgumentException() {
        assertEquals(new HashMap<>(), StringProcessor.countCharacters(""));
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("countCharacter positive tests with different arguments")
    @MethodSource("countCharactersDataProvider")
    void countCharactersTest(Map<Character, Integer> expected, String str, String description) {
        assertEquals(expected, StringProcessor.countCharacters(str));
    }

    static Stream<Arguments> countCharactersDataProvider() {
        return Stream.of(
                Arguments.of(Map.of('h', 1, 'e', 1, 'L', 2, 'o', 1), "heLLo", "String has duplicates in upper case"),
                Arguments.of(Map.of('a', 2, 'd', 1, 'm', 2), "madam", "String has several duplicates in lower case"),
                Arguments.of(Map.of('a', 2, 'd', 1, 'm', 1, 'M', 1), "Madam", "Mixed case letters"),
                Arguments.of(Map.of('a', 1, 'c', 1, 't', 1), "cat", "String has no duplicates"),
                Arguments.of(Map.of(' ', 2, 'a', 1, 'b', 1, 'B', 1, 'c', 1,
                                't', 2, 'e', 1, 'h', 1, 'o', 1),
                        "Bob the cat", "Complicated string with several spaces in the middle and duplicates"),
                Arguments.of(Map.of('2', 1, '5', 1, '6', 1, '8', 1), "6825", "Numbers without duplicates"),
                Arguments.of(Map.of('2', 1, '5', 1, '6', 2, '8', 2), "868625", "Numbers without duplicates"),
                Arguments.of(Map.of('%', 1, '№', 1, '*', 1, '?', 1), "№%?*", "Special characters without duplicates"),
                Arguments.of(Map.of(' ', 3, 'a', 1, 'c', 1, 't', 1), "   cat", "Several spaces in the beginning"),
                Arguments.of(Map.of(' ', 3, 'a', 1, 'c', 1, 't', 1), "cat   ", "Several spaces in the end"),
                Arguments.of(Map.of(' ', 1), " ", "Only one space as a string"),
                Arguments.of(Map.of('a', 1), "a", "Only one letter as a string"),
                Arguments.of(Map.of('2', 1), "2", "Only one number as a string"),
                Arguments.of(Map.of('$', 1), "$", "Only one special character as a string"),
                Arguments.of(Map.of(' ', 3), "   ", "Several spaces as a string")
        );
    }

    @Test
    @DisplayName("findLongestWord should throw IllegalArgumentException, when the string is null")
    void findLongestWordTest_nullString_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.findLongestWord(null));
        assertEquals("Input string cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("findLongestWord should throw IllegalArgumentException, when the string is empty")
    void findLongestWordTest_emptyString_throwsIllegalArgumentException() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.findLongestWord(""));
                    assertEquals("Input string cannot be empty.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.findLongestWord("   "));
                    assertEquals("Input string cannot be empty.", exception.getMessage());
                }
        );
    }


    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("findLongestWord positive tests with different arguments")
    @CsvSource({
            "'programming', 'Java is a programming language', 'Simple sentence test'",
            "'programming', 'Java is  a   programming     language', 'Multiple spaces between words'",
            "'programming', 'Java\tis\na\tprogramming\tlanguage', 'Tabulation and hyphenation'",
            "'Java', 'Java', 'One word test'",
            "'Java', ' Java', 'One space in the beginning test'",
            "'Java', 'Java ', 'One space in the end test'",
            "'Java', ' Java ', 'Two spaces in the beginning and in the end test'",
            "'cat', 'My cat and my dog', 'Method returns the first of several longest words'",
            "'4567', '123 4567 89', 'Numbers'",
            "'(*&^)', '$%^ (*&^) @#$', 'Characters'"
    })
    void findLongestWordTest(String expected, String str, String description) {
        assertEquals(expected, StringProcessor.findLongestWord(str));
    }

    @Test
    @DisplayName("formatPhoneNumber methods should throws IllegalArgumentException, when the string is null")
    void formatPhoneNumber_nullString_throwsIllegalArgumentException() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_replace(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_append(null));
                    assertEquals("Input string cannot be null.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("formatPhoneNumber methods should throws IllegalArgumentException, when the number doesn't consist from 11 digits")
    @CsvSource({
            "'', 'Empty string'",
            "'   ', 'Multiple spaces string'",
            "'7123456789', '10 digits string'",
            "'one7123456789two', '10 digits with letters string'",
            "'7OOO1234567', 'String with letters O instead of digits 0'",
            "'711122233445', 'Too long phone number'"
    })
    void formatPhoneNumber_shortNumber_throwsIllegalArgumentException(String str, String description) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_replace(str));
                    assertEquals("The phone number must be 11 digits long.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_append(str));
                    assertEquals("The phone number must be 11 digits long.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("formatPhoneNumber methods should throws IllegalArgumentException, when the number doesn't begin with 7 or 8")
    @CsvSource({
            "'01234567890', '11 digits string with first not 7 or 8'",
            "'one01234567890two', '11 digits with letters string with first not 7 or 8'"
    })
    void formatPhoneNumber_wrongBeginning_throwsIllegalArgumentException(String str, String description) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_replace(str));
                    assertEquals("Please check that your phone number is entered correctly. Russian numbers must begin with 7 or 8.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> StringProcessor.formatPhoneNumber_append(str));
                    assertEquals("Please check that your phone number is entered correctly. Russian numbers must begin with 7 or 8.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("formatPhoneNumber methods positive tests with different arguments")
    @CsvSource({
            "'+7 (999) 888-77-66', '79998887766', 'phone number only with digits starts with 7'",
            "'+7 (999) 888-77-66', '89998887766', 'phone number only with digits starts with 8'",
            "'+7 (999) 888-77-66', '7 999 888 77 66', 'phone number with digits and spaces",
            "'+7 (999) 888-77-66', '8-999-888-77-66', 'phone number with digits and dashes'",
            "'+7 (999) 888-77-66', '8 (999) 888-77-66', 'phone number with digits, spaces, dashes and brackets'",
            "'+7 (999) 888-77-66', 'o8o(999)o888-77-66o', 'phone number with digits, spaces, dashes, letters and brackets'"
    })
    void formatPhoneNumberTest(String expected, String str, String description) {
        assertAll(
                () -> assertEquals(expected, StringProcessor.formatPhoneNumber_replace(str)),
                () -> assertEquals(expected, StringProcessor.formatPhoneNumber_append(str)));
    }
}
