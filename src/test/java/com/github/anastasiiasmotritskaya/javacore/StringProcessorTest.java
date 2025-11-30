package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    @Test
    void testIsPalindromeWithPalindromeString() {
        assertTrue(StringProcessor.isPalindrome("madam"));
    }

    @Test
    void testIsPalindromeWithNonPalindromeString() {
        assertFalse(StringProcessor.isPalindrome("hello"));
    }

    @Test
    void testIsPalindromeWithSingleCharacter() {
        assertTrue(StringProcessor.isPalindrome("a"));
    }

    @Test
    void testIsPalindromeWithNoCharacter() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StringProcessor.isPalindrome(null)
        );

        assertEquals("Illegal argument: empty variable, String argument required", exception.getMessage());
    }

    @Test
    void testReverseStringPositive() {
        assertEquals("tseT", StringProcessor.reverseString("Test"));
    }

    @Test
    void testReverseStringEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.reverseString(null));
        assertEquals("Illegal argument: empty variable, String argument required", exception.getMessage());
    }

    @Test
    void testReverseStringSpaces() {

        assertEquals(" tseT", StringProcessor.reverseString("Test "));
        assertEquals("tseT ", StringProcessor.reverseString(" Test"));
        assertEquals("ts   eT", StringProcessor.reverseString("Te   st"));
    }

    @Test
    void testReverseStringShortString() {
        assertEquals("F", StringProcessor.reverseString("F"));
    }

    @Test
    void testReverseStringEmptyString() {
        assertEquals("", StringProcessor.reverseString(""));
    }

    @Test
    void testReverseStringWithNumbers() {
        assertEquals("321", StringProcessor.reverseString("123"));
    }

    @Test
    void testReverseStringWithSpecialChars() {
        assertEquals("!@#$", StringProcessor.reverseString("$#@!"));
    }

    @Test
    void testCountVowelsPositive() {
        assertAll("testCountVowels should count vowels in all types of strings",
                () -> assertEquals(2, StringProcessor.countVowels("camel")),
                () -> assertEquals(0, StringProcessor.countVowels("123")),
                () -> assertEquals(0, StringProcessor.countVowels("0")),
                () -> assertEquals(0, StringProcessor.countVowels(":*?)№*"))
        );
    }

    @Test
    void testCountVowelsNullString() {
        assertEquals(0, StringProcessor.countVowels(null));
    }

    @Test
    void testCountVowelsEmptyString() {
        assertEquals(0, StringProcessor.countVowels(""));
    }

    @Test
    void testCountVowelsDifferentCases() {
        assertEquals(8, StringProcessor.countVowels("A camel is an animal"));
    }

    @Test
    void testCountVowelsMixedLanguages() {
        assertEquals(4, StringProcessor.countVowels("Привет Hello"));
    }

    @Test
    void testRemoveSpaces() {
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
    void testRemoveSpacesNull() {
        assertNull(StringProcessor.removeSpaces(null));
    }

    @Test
    void testCapitalizeWords() {
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
    void testCapitalizeWordsNull() {
        assertNull(StringProcessor.capitalizeWords(null));
    }
}
