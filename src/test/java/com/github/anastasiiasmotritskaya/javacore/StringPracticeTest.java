package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringPracticeTest {

    @Test
    void testIsPalindromeWithPalindromeString() {
        assertTrue(StringPractice.isPalindrome("madam"));
    }

    @Test
    void testIsPalindromeWithNonPalindromeString() {
        assertFalse(StringPractice.isPalindrome("hello"));
    }

    @Test
    void testIsPalindromeWithSingleCharacter() {
        assertTrue(StringPractice.isPalindrome("a"));
    }

    @Test
    void testIsPalindromeWithNoCharacter() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StringPractice.isPalindrome(null)
        );

        assertEquals("Строка не может быть null", exception.getMessage());
    }
}
