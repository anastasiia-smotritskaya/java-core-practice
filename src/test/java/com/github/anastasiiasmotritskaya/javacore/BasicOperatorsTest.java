package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void convertTemperatureFromTest() {
        IllegalArgumentException fromException = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(100, "Цельсий", "K"));
        assertEquals("This is not a scale! You must select Celsius (C), Fahrenheit (F), or Kelvin (K).", fromException.getMessage());
    }

    @Test
    void convertTemperatureToTest() {
        IllegalArgumentException toException = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(100.00, "C", "Кельвин"));
        assertEquals("This is not a scale! You must select Celsius (C), Fahrenheit (F), or Kelvin (K).", toException.getMessage());
    }

    @Test
    void convertTemperatureFromToTest() {
        IllegalArgumentException fromToException = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(100.00, "Цельсий", "Фаренгейт"));

        assertEquals("This is not a scale! You must select Celsius (C), Fahrenheit (F), or Kelvin (K).", fromToException.getMessage());
    }

    @Test
    void convertTemperatureCelsiusTest() {
        assertAll("Celsius to Kelvin, Celsius to Fahrenheit",
                () -> assertEquals(0.0, BasicOperators.convertTemperature(-273.15, "C", "K"), 0.001),
                () -> assertEquals(273.15, BasicOperators.convertTemperature(0.0, "C", "K"), 0.001),
                () -> assertEquals(293.15, BasicOperators.convertTemperature(20.0, "C", "K"), 0.001),
                () -> assertEquals(373.15, BasicOperators.convertTemperature(100.00, "C", "K"), 0.001),

                () -> assertEquals(-459.67, BasicOperators.convertTemperature(-273.15, "C", "F"), 0.001),
                () -> assertEquals(32.0, BasicOperators.convertTemperature(0.0, "C", "F"), 0.001),
                () -> assertEquals(68.0, BasicOperators.convertTemperature(20.0, "C", "F"), 0.001),
                () -> assertEquals(212.0, BasicOperators.convertTemperature(100.0, "C", "F"), 0.001),
                () -> assertEquals(98.6, BasicOperators.convertTemperature(37.0, "C", "F"), 0.001)
        );
    }

    @Test
    void convertTemperatureFahrenheitTest() {
        assertAll("Fahrenheit to Celsius, Fahrenheit to Kelvin",
                () -> assertEquals(-273.15, BasicOperators.convertTemperature(-459.67, "F", "C"), 0.001),
                () -> assertEquals(0.0, BasicOperators.convertTemperature(32.00, "F", "C"), 0.001),
                () -> assertEquals(20.0, BasicOperators.convertTemperature(68.00, "F", "C"), 0.001),
                () -> assertEquals(100.0, BasicOperators.convertTemperature(212.00, "F", "C"), 0.001),
                () -> assertEquals(37.0, BasicOperators.convertTemperature(98.6, "F", "C"), 0.001),

                () -> assertEquals(0.0, BasicOperators.convertTemperature(-459.67, "F", "K"), 0.001),
                () -> assertEquals(273.15, BasicOperators.convertTemperature(32.0, "F", "K"), 0.001),
                () -> assertEquals(293.15, BasicOperators.convertTemperature(68.0, "F", "K"), 0.001),
                () -> assertEquals(373.15, BasicOperators.convertTemperature(212.0, "F", "K"), 0.001)
        );
    }

    @Test
    void convertTemperatureKelvinTest() {
        assertAll("Kelvin to Celsius, Kelvin to Fahrenheit",
                () -> assertEquals(-273.15, BasicOperators.convertTemperature(0.0, "K", "C"), 0.001),
                () -> assertEquals(0.0, BasicOperators.convertTemperature(273.15, "K", "C"), 0.001),
                () -> assertEquals(20.0, BasicOperators.convertTemperature(293.15, "K", "C"), 0.001),
                () -> assertEquals(100.0, BasicOperators.convertTemperature(373.15, "K", "C"), 0.001),

                () -> assertEquals(-459.67, BasicOperators.convertTemperature(0.0, "K", "F"), 0.001),
                () -> assertEquals(32.0, BasicOperators.convertTemperature(273.15, "K", "F"), 0.001),
                () -> assertEquals(68.0, BasicOperators.convertTemperature(293.15, "K", "F"), 0.001),
                () -> assertEquals(212.0, BasicOperators.convertTemperature(373.15, "K", "F"), 0.001)
        );
    }

    @Test
    void convertTemperatureLowerCaseTest() {
        assertAll(
                () -> assertEquals(-459.67, BasicOperators.convertTemperature(-273.15, "c", "F"), 0.001),
                () -> assertEquals(0.0, BasicOperators.convertTemperature(-459.67, "f", "K"), 0.001),
                () -> assertEquals(-273.15, BasicOperators.convertTemperature(0.0, "k", "C"), 0.001),

                () -> assertEquals(32.0, BasicOperators.convertTemperature(0.0, "C", "f"), 0.001),
                () -> assertEquals(273.15, BasicOperators.convertTemperature(32.0, "F", "k"), 0.001),
                () -> assertEquals(0.0, BasicOperators.convertTemperature(273.15, "K", "c"), 0.001)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1000.00, -500.00, -273.15, -1.00, -0.01})
    void convertTemperatureToKelvinNegativeTest(double negativeValue) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(negativeValue, "K", "C"));
        assertEquals("Kelvins are never negative. Check the data.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1000.00, -500.00, -273.16})
    void convertTemperatureFromCelsiusToKelvinNegativeTest(double negativeValue) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(negativeValue, "C", "K"));
        assertEquals("Kelvins are never negative. Check the data.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1000.00, -500.00, -459.68})
    void convertTemperatureFromFahrenheitToKelvinNegativeTest(double negativeValue) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(negativeValue, "F", "K"));
        assertEquals("Kelvins are never negative. Check the data.", exception.getMessage());
    }

    @Test
    void convertTemperatureSameScalesTest() {
        assertAll(
                () -> assertEquals(100.0, BasicOperators.convertTemperature(100.0, "C", "C"), 0.001),
                () -> assertEquals(100.0, BasicOperators.convertTemperature(100.0, "F", "F"), 0.001),
                () -> assertEquals(100.0, BasicOperators.convertTemperature(100.0, "K", "K"), 0.001)
        );
    }

    @Test
    void convertTemperatureToNullTest() {
        IllegalArgumentException fromToException = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(100.00, null, "C"));

        assertEquals("Scale cannot be null", fromToException.getMessage());
    }

    @Test
    void convertTemperatureFromNullTest() {
        IllegalArgumentException fromToException = assertThrows(IllegalArgumentException.class,
                () -> BasicOperators.convertTemperature(100.00, "C", null));

        assertEquals("Scale cannot be null", fromToException.getMessage());
    }

}
