package com.github.anastasiiasmotritskaya.javacore.basicsyntaxtest;

import com.github.anastasiiasmotritskaya.javacore.basicsyntax.NumberAnalyzer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberAnalyzerTest {

    @Test
    public void isPrimeNullTest() {
        String expected = "The number 1 is neither prime nor composite, and numbers less than 1 are not prime numbers.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> NumberAnalyzer.isPrime(0));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void isPrimeOneTest() {
        String expected = "The number 1 is neither prime nor composite, and numbers less than 1 are not prime numbers.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> NumberAnalyzer.isPrime(1));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void isPrimeVariousNumbersTest() {
        assertAll(
                () -> assertTrue(NumberAnalyzer.isPrime(2)),
                () -> assertTrue(NumberAnalyzer.isPrime(3)),
                () -> assertTrue(NumberAnalyzer.isPrime(17)),
                () -> assertTrue(NumberAnalyzer.isPrime(97)),

                () -> assertFalse(NumberAnalyzer.isPrime(4)),
                () -> assertFalse(NumberAnalyzer.isPrime(9)),
                () -> assertFalse(NumberAnalyzer.isPrime(15)),
                () -> assertFalse(NumberAnalyzer.isPrime(100))
        );
    }

    @Test
    public void findMaxEqualABCTest() {
        assertEquals(3, NumberAnalyzer.findMax(3, 3, 3));
    }

    @Test
    public void findMaxEqualTwoNumbersTest() {
        assertAll(
                () -> assertEquals(3, NumberAnalyzer.findMax(3, 3, 1)),
                () -> assertEquals(5, NumberAnalyzer.findMax(3, 3, 5)),
                () -> assertEquals(3, NumberAnalyzer.findMax(1, 3, 3)),
                () -> assertEquals(5, NumberAnalyzer.findMax(5, 3, 3)),
                () -> assertEquals(5, NumberAnalyzer.findMax(3, 5, 3)),
                () -> assertEquals(3, NumberAnalyzer.findMax(3, 1, 3))
        );
    }

    @Test
    public void findMaxTest() {
        assertAll(
                () -> assertEquals(3, NumberAnalyzer.findMax(3, 2, 1)),
                () -> assertEquals(3, NumberAnalyzer.findMax(2, 3, 1)),
                () -> assertEquals(3, NumberAnalyzer.findMax(1, 2, 3))
        );
    }

    @Test
    public void findMaxNegativeNumbersTest() {
        assertEquals(-1, NumberAnalyzer.findMax(-1000, -23, -1));
    }

    @Test
    public void findMaxLargeNumbersTest() {
        assertEquals(1000, NumberAnalyzer.findMax(1000, 99, 500));
    }

    @Test
    public void sumDigitsTest() {
        assertAll(
                () -> assertEquals(0, NumberAnalyzer.sumDigits(0)),
                () -> assertEquals(6, NumberAnalyzer.sumDigits(123)),
                () -> assertEquals(6, NumberAnalyzer.sumDigits(-123)),
                () -> assertEquals(1, NumberAnalyzer.sumDigits(10)),
                () -> assertEquals(45, NumberAnalyzer.sumDigits(123456789))
        );
    }

    @Test
    public void isPositiveTest() {
        assertAll(
                () -> assertTrue(NumberAnalyzer.isPositive(10)),
                () -> assertFalse(NumberAnalyzer.isPositive(0)),
                () -> assertFalse(NumberAnalyzer.isPositive(-10))
        );
    }

    @Test
    public void formatNumberTest() {
        assertAll(
                () -> assertEquals("The number 10 is even and positive", NumberAnalyzer.formatNumber(10)),
                () -> assertEquals("The number -5 is odd and not positive", NumberAnalyzer.formatNumber(-5)),
                () -> assertEquals("The number 0 is even and not positive", NumberAnalyzer.formatNumber(0))
        );
    }
}
