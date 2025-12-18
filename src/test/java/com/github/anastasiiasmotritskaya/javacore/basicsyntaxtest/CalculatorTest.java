package com.github.anastasiiasmotritskaya.javacore.basicsyntaxtest;

import com.github.anastasiiasmotritskaya.javacore.basicsyntax.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void addTest() {
        Assertions.assertEquals(20.0, Calculator.add(15, 5), 0.001);
    }

    @Test
    void subtractTest() {
        assertEquals(10.0, Calculator.subtract(15, 5), 0.001);
    }

    @Test
    void multiplyTest() {
        assertEquals(75.0, Calculator.multiply(15, 5), 0.001);
    }

    @Test
    void divideTest() {
        assertEquals(3.0, Calculator.divide(15, 5), 0.001);
    }

    @Test
    void divideNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.divide(10.0, 0.0));

        assertEquals("You can't divide by zero", exception.getMessage());
    }

    @Test
    void isEvenEvenNumberTest() {
        assertTrue(Calculator.isEven(10));
    }

    @Test
    void isEvenOddNumberTest() {
        assertFalse(Calculator.isEven(15));
    }

    @Test
    void isEvenNullTest() {
        assertTrue(Calculator.isEven(0));
    }
}
