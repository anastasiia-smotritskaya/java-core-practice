package com.github.anastasiiasmotritskaya.javacore.basicsyntaxtest;

import com.github.anastasiiasmotritskaya.javacore.basicsyntax.Cycles;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Java Core")
@Feature("Basic syntax")
@Story("Cycles")
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
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Cycles.findPrimeNumbers(start, end));

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void findPrimeNumbersEmptyListTest() {
        assertAll(
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

    @Test
    public void multiplicationTableOneTest() {
        int[][] expected = {{1}};

        assertArrayEquals(expected, Cycles.multiplicationTable(1));
    }

    @Test
    public void multiplicationTableThreeTest() {
        int[][] expected = {{1, 2, 3}, {2, 4, 6}, {3, 6, 9}};

        assertArrayEquals(expected, Cycles.multiplicationTable(3));
    }

    @Test
    public void multiplicationTableTenTest() {
        int[][] expected = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
                {3, 6, 9, 12, 15, 18, 21, 24, 27, 30},
                {4, 8, 12, 16, 20, 24, 28, 32, 36, 40},
                {5, 10, 15, 20, 25, 30, 35, 40, 45, 50},
                {6, 12, 18, 24, 30, 36, 42, 48, 54, 60},
                {7, 14, 21, 28, 35, 42, 49, 56, 63, 70},
                {8, 16, 24, 32, 40, 48, 56, 64, 72, 80},
                {9, 18, 27, 36, 45, 54, 63, 72, 81, 90},
                {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}};

        assertArrayEquals(expected, Cycles.multiplicationTable(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11})
    public void multiplicationTableIllegalArgumentTest(int tableLength) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Cycles.multiplicationTable(tableLength));

        assertEquals("The entered value must be between 1 and 10.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "12345, 54321",
            "-12345, -54321",
            "0, 0",
            "1, 1",
            "10, 1",
            "1000, 1",
            "120, 21",
            "10020, 2001",
            "121, 121",
            "222, 222",
            "-1, -1",
            "-10, -1",
            "463_847_412, 214_748_364",
            "-463_847_412, -214_748_364",
    })
    public void reverseDigitsTest(int number, int reverseNumber) {
        assertEquals(reverseNumber, Cycles.reverseDigits(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MIN_VALUE, 1_999_999_999, -1_999_999_999})
    public void reverseDigitsBorderTest(int number) {
        assertThrows(ArithmeticException.class, () -> Cycles.reverseDigits(number));
    }
}
