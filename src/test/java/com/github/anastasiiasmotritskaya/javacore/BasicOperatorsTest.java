package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicOperatorsTest {

    @Test
    void divideWithRemainderTest() {
        assertAll(
                () -> assertArrayEquals(new int[]{2, 0}, BasicOperators.divideWithRemainder(10, 5)),
                () -> assertArrayEquals(new int[]{2, 1}, BasicOperators.divideWithRemainder(5, 2)),
                () -> assertArrayEquals(new int[]{3, 1}, BasicOperators.divideWithRemainder(10, 3)),
                () -> assertArrayEquals(new int[]{0, 0}, BasicOperators.divideWithRemainder(0, 3))
        );
    }

    @Test
    void divideWithRemainderNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.divideWithRemainder(10, 0));
        assertEquals("You can't divide by zero", exception.getMessage());
    }

    @Test
    void divideWithRemainderNegativeTest() {
        assertAll(
                () -> assertArrayEquals(new int[]{-2, 0}, BasicOperators.divideWithRemainder(-10, 5)),
                () -> assertArrayEquals(new int[]{2, 0}, BasicOperators.divideWithRemainder(-10, -5)),
                () -> assertArrayEquals(new int[]{-2, 1}, BasicOperators.divideWithRemainder(5, -2)),
                () -> assertArrayEquals(new int[]{-3, -1}, BasicOperators.divideWithRemainder(-10, 3))
        );
    }
}
