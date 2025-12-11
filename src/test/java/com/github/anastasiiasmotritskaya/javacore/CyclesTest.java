package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
