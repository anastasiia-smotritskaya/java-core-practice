package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    @Test
    void isPalindromeWithPalindromeStringTest() {
        assertTrue(StringProcessor.isPalindrome("madam"));
    }

    @Test
    void isPalindromeWithNonPalindromeStringTest() {
        assertFalse(StringProcessor.isPalindrome("hello"));
    }

    @Test
    void isPalindromeWithSingleCharacterTest() {
        assertTrue(StringProcessor.isPalindrome("a"));
    }

    @Test
    void isPalindromeWithNoCharacterTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StringProcessor.isPalindrome(null)
        );

        assertEquals("Illegal argument: empty variable, String argument required", exception.getMessage());
    }

    @Test
    void reverseStringPositiveTest() {
        assertEquals("tseT", StringProcessor.reverseString("Test"));
    }

    @Test
    void reverseStringEmptyTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.reverseString(null));
        assertEquals("Illegal argument: empty variable, String argument required", exception.getMessage());
    }

    @Test
    void reverseStringSpacesTest() {
        assertAll(
                () -> assertEquals(" tseT", StringProcessor.reverseString("Test ")),
                () -> assertEquals("tseT ", StringProcessor.reverseString(" Test")),
                () -> assertEquals("ts   eT", StringProcessor.reverseString("Te   st"))
        );
    }

    @Test
    void reverseStringShortStringTest() {
        assertEquals("F", StringProcessor.reverseString("F"));
    }

    @Test
    void reverseStringEmptyStringTest() {
        assertEquals("", StringProcessor.reverseString(""));
    }

    @Test
    void reverseStringWithNumbersTest() {
        assertEquals("321", StringProcessor.reverseString("123"));
    }

    @Test
    void reverseStringWithSpecialCharsTest() {
        assertEquals("!@#$", StringProcessor.reverseString("$#@!"));
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
