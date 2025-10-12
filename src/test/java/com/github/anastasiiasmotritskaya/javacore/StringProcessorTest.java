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


}
