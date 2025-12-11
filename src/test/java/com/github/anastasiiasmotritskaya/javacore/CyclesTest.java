package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CyclesTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    public void sumOfNumbersIllegalNumberTest(int n) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Cycles.sumOfNumbers_for(n));
                    assertEquals("The number of numbers must not be less than or equal to zero.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Cycles.sumOfNumbers_while(n));
                    assertEquals("The number of numbers must not be less than or equal to zero.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Cycles.sumOfNumbers_do(n));
                    assertEquals("The number of numbers must not be less than or equal to zero.", exception.getMessage());
                }
        );
    }

    @Test
    public void sumOfNumbersIntegerMaxTest() {
        assertAll(
                () -> assertThrows(ArithmeticException.class, () -> Cycles.sumOfNumbers_for(65536)),
                () -> assertThrows(ArithmeticException.class, () -> Cycles.sumOfNumbers_while(65536)),
                () -> assertThrows(ArithmeticException.class, () -> Cycles.sumOfNumbers_do(65536))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 100, 65535})
    public void sumOfNumbersTest() {
        assertAll(
                () -> assertEquals(1, Cycles.sumOfNumbers_for(1)),
                () -> assertEquals(3, Cycles.sumOfNumbers_for(2)),
                () -> assertEquals(5050, Cycles.sumOfNumbers_for(100)),
                () -> assertEquals(2_147_450_880, Cycles.sumOfNumbers_for(65535)),

                () -> assertEquals(1, Cycles.sumOfNumbers_while(1)),
                () -> assertEquals(3, Cycles.sumOfNumbers_while(2)),
                () -> assertEquals(5050, Cycles.sumOfNumbers_while(100)),
                () -> assertEquals(2_147_450_880, Cycles.sumOfNumbers_while(65535)),

                () -> assertEquals(1, Cycles.sumOfNumbers_do(1)),
                () -> assertEquals(3, Cycles.sumOfNumbers_do(2)),
                () -> assertEquals(5050, Cycles.sumOfNumbers_do(100)),
                () -> assertEquals(2_147_450_880, Cycles.sumOfNumbers_do(65535))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "-10, 10, 'Start must be at least 2. Given: -10.'",
            "-1, 10, 'Start must be at least 2. Given: -1.'",
            "0, 10, 'Start must be at least 2. Given: 0.'",
            "1, 10, 'Start must be at least 2. Given: 1.'",
            "5, 3, 'The start must be <= end. Given: start = 5, end = 3.'",
    })
    public void findPrimeNumbersIllegalNumberTest(int start, int end, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Cycles.findPrimeNumbers(start, end));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void findPrimeNumbersEmptyListTest() {
        assertAll (
                () -> assertEquals(Collections.emptyList(), Cycles.findPrimeNumbers(14, 16)),
                () -> assertEquals(Collections.emptyList(), Cycles.findPrimeNumbers(8, 10))
        );
    }

    @Test
    public void findPrimeNumbersTest() {
        assertAll(
                () -> assertEquals(List.of(2, 3, 5, 7), Cycles.findPrimeNumbers(2, 10)),
                () -> assertEquals(List.of(37, 41, 43, 47), Cycles.findPrimeNumbers(35, 47))
        );
    }

    @Test
    public void findPrimeNumbersLargeRangeTest() {
        List<Integer> primeNumbers = Cycles.findPrimeNumbers(2, 100_000);
        assertEquals(9592, primeNumbers.size());
    }
}
