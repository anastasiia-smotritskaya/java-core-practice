package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Абстрактный тестовый класс для всех реализаций filterEvenNumbers
 * Наследники должны реализовать filterEvenNumbers()
 *
 * @see com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils#filterEvenNumbers_for(List)
 * @see com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils#filterEvenNumbers_lambda(List)
 * @see com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils#filterEvenNumbers_mr(List)
 * @see FilterEvenNumbersForLoopTest
 * @see FilterEvenNumbersLambdaTest
 * @see FilterEvenNumbersMRTest
 */
@DisplayName("Тестирование filterEvenNumbers (все реализации)")
public abstract class FilterEvenNumbersAbstractTest {

    protected abstract List<Integer> filterEvenNumbers(List<Integer> numbers);

    @Test
    @DisplayName("filterEvenNumbers should throw IllegalArgumentException if the list of numbers is null")
    public void filterEvenNumbers_NullSourceTest() {
        String expected = "List of numbers must not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> filterEvenNumbers(null));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("filterEvenNumbers should return an empty list if the list of numbers is empty")
    @MethodSource("noEvenNumbersDataProvider")
    public void filterEvenNumbers_noEvenNumbersTest(List<Integer> numbers, String description) {
        assertEquals(new ArrayList<>(), filterEvenNumbers(numbers));
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("filterEvenNumbers should return a list of even numbers if there are in the list")
    @MethodSource("oddAndEvenNumbersDataProvider")
    public void filterEvenNumbers_PositiveTest(List<Integer> numbers, List<Integer> expected, String description) {
        List<Integer> actual = filterEvenNumbers(numbers);

        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }

    protected static Stream<Arguments> noEvenNumbersDataProvider() {
        return Stream.of(
                Arguments.of(new ArrayList<>(), "Empty list"),
                Arguments.of(List.of(15), "Only one odd number list"),
                Arguments.of(List.of(-15, -5, -1, 1, 5, 15), "Odd numbers list")
        );
    }

    protected static Stream<Arguments> oddAndEvenNumbersDataProvider() {
        return Stream.of(
                Arguments.of(List.of(2), List.of(2), "Only one even number list"),
                Arguments.of(List.of(0), List.of(0), "Only one zero list"),
                Arguments.of(List.of(2, 4, 16, 32, -2, -4, -16),
                        List.of(2, 4, 16, 32, -2, -4, -16), "Even numbers list"),
                Arguments.of(List.of(-15, 2, 11, 38, 0, 111, -4),
                        List.of(2, 38, 0, -4), "Mixed list of numbers")
        );
    }
}
