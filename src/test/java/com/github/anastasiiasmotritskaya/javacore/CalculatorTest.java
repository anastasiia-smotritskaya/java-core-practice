package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAdd() {
        assertEquals(20.0, Calculator.add(15, 5), 0.001);
    }

    @Test
    void testSubtract() {
        assertEquals(10.0, Calculator.subtract(15, 5), 0.001);
    }

    @Test
    void testMultiply() {
        assertEquals(75.0, Calculator.multiply(15, 5), 0.001);
    }

    @Test
    void testDivide() {
        assertEquals(3.0, Calculator.divide(15, 5), 0.001);
    }

    @Test
    void testDivideWithIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.divide(10.0, 0.0));

        assertEquals("You can't divide by zero", exception.getMessage());
    }

    @Test
    void testIsEvenEvenNumber() {
        assertTrue(Calculator.isEven(10));
    }

    @Test
    void testIsEvenOddNumber() {
        assertFalse(Calculator.isEven(15));
    }

    @Test
    void testIsEvenNull() {
        assertTrue(Calculator.isEven(0));
    }
}
