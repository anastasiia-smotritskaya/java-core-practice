package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.ConsoleCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleCalculatorTest {
    @ParameterizedTest(name = "[{index}] {4}")
    @DisplayName("calculate positive tests with different arguments")
    @CsvSource({
            "4, 2, '+', 2, 'Addiction: positive numbers'",
            "6, 10, '-', 4, 'Subtraction: positive numbers'",
            "6, 2, '*', 3, 'Multiplication: positive numbers'",
            "2, 10, '/', 5, 'Division: positive numbers'",
            "-4, -2, '+', -2, 'Addiction: negative numbers'",
            "-6, -10, '-', -4, 'Subtraction: negative numbers'",
            "6, -2, '*', -3, 'Multiplication: negative numbers'",
            "2, -10, '/', -5, 'Division: negative numbers'",
            "3, 5, '+', -2, 'Addiction: mixed numbers'",
            "-14, -10, '-', 4, 'Subtraction: mixed numbers'",
            "-6, 2, '*', -3, 'Multiplication: mixed numbers'",
            "-2, 10, '/', -5, 'Division: mixed numbers'",
            "2.0, 10.0, '/', 5.0, 'Division: all numbers are in double format'",
            "2.0, 10, '/', 5.0, 'Division: numbers are in double and integer format with double result'",
            "2, 10, '/', 5.0, 'Division: numbers are in double and integer format with integer result'"
    })
    void calculatePositiveTest(double expected, double a, String operation, double b, String description) {
        assertAll(
                () -> assertEquals(expected, ConsoleCalculator.calculate(a, operation, b))
        );
    }

    @Test
    @DisplayName("calculate should throw IllegalArgumentException for division by zero")
    void calculate_divisionByZeroTest_IllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConsoleCalculator.calculate(10, "/", 0));
        assertEquals("You can't divide by zero.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("calculate should throw IllegalArgumentException for division by zero")
    @ValueSource(strings = {"%", "++","@", "&"})
    void calculate_unknownOperationTest_IllegalArgumentException(String operation){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConsoleCalculator.calculate(10, operation, 0));
        assertEquals("Unknown operation. Use +, -, *, or /.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("parseInput should throw IllegalArgumentException when input are null or empty")
    void parseInput_NullOrEmptyInputTest_IllegalArgumentException(String input){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConsoleCalculator.parseInput(input));
        assertEquals("Input cannot be null or empty.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("parseInput negative tests with various arguments")
    @CsvSource({
            "'3', 'Only first number'",
            "'+3', 'Only second number'",
            "'3 +', 'Only first number and operation'",
            "'3 + + 3', 'Two operations in a row'",
            "'3 + 3 + 3', 'Two operations not in a row'",
            "'3+3', 'Has no spaces'",
            "'  + 5', 'Space instead the first argument'",
            "'5 +  ', 'Space instead the second argument'"
    })
    void parseInput_incorrectFormatTest_IllegalArgumentException(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                ConsoleCalculator.parseInput(input));
        assertEquals("Incorrect format. Use: NUMBER OPERATION NUMBER.", exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("parseInput positive tests with various arguments")
    @MethodSource("parseInputDataProvider")
    void parseInputPositiveTest(Object[] expected, String input, String description) {
        Object[] actual = ConsoleCalculator.parseInput(input);
        assertAll(
                () -> assertEquals(expected[0], actual[0]),
                () -> assertEquals(expected[1], actual[1]),
                () -> assertEquals(expected[2], actual[2])
        );
    }

    static Stream<Arguments> parseInputDataProvider() {
        return Stream.of(
                Arguments.of(new Object[]{3.0, "+", 5.0}, "3 + 5", "Positive integer numbers, addiction"),
                Arguments.of(new Object[]{3.0, "-", 5.0}, "3.0 - 5.0", "Positive double numbers, subtraction"),
                Arguments.of(new Object[]{30.0, "/", 5.0}, "30 / 5", "Positive numbers, division"),
                Arguments.of(new Object[]{3.0, "*", 5.0}, "+3 * 5", "Positive numbers, multiplication"),
                Arguments.of(new Object[]{-3.0, "*", 5.0}, "-3 * 5", "One number is negative, multiplication"),
                Arguments.of(new Object[]{-3.0, "*", -5.0}, "-3 * -5", "Negative numbers, multiplication")
        );
    }

    @ParameterizedTest
    @DisplayName("parseInput negative parsing tests with various arguments")
    @CsvSource({
            "'five + 5', 'Incorrect first argument'",
            "'5 + five', 'Incorrect second argument'",
            "'5,0 + 5.0', 'Comma instead of period in first argument'",
            "'5.0 + 5,0', 'Comma instead of period in second argument'",

    })
    void parseInput_incorrectFormatTest_NumberFormatException(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                ConsoleCalculator.parseInput(input));
        assertEquals("Incorrect number format. Use numbers like 5.5 or 10.", exception.getMessage());
    }
}
