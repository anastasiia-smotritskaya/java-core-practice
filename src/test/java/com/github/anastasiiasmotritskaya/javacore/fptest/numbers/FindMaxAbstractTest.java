package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций findMax
 * Наследники должны реализовать findMax()
 *
 * @see NumberUtils#findMax_for(List)
 * @see NumberUtils#findMax_stream(List)
 * @see FindMaxForTest
 * @see FindMaxStreamTest
 */
public abstract class FindMaxAbstractTest {
    protected abstract Optional<Integer> findMax(List<Integer> numbers);

    @Test
    @DisplayName("findMax should throw IllegalArgumentException if the list is null")
    public void findMax_NullSourceTest() {
        String expected = "List of numbers must not be null.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> findMax(null));
        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("findMax should throw proper max number with different lists")
    @MethodSource("findMaxDataProvider")
    public void findMaxTest(List<Integer> numbers, Optional<Integer> expected, String description) {
        assertEquals(expected, findMax(numbers));
    }

    private static Stream<Arguments> findMaxDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), Optional.empty(), "Empty list"),
                Arguments.of(List.of(1), Optional.of(1), "Only one number in the list"),
                Arguments.of(List.of(15, 100, 1), Optional.of(100), "Positive numbers"),
                Arguments.of(List.of(-15, -100, -1), Optional.of(-1), "Negative numbers"),
                Arguments.of(List.of(-15, 100, -1, 15, 0), Optional.of(100), "Mixed numbers"),
                Arguments.of(List.of(Integer.MIN_VALUE, -15, Integer.MAX_VALUE, -1, 0, 1000),
                        Optional.of(Integer.MAX_VALUE), "Integer.MAX_VALUE and Integer.MIN_VALUE"),
                Arguments.of(List.of(15, 15, 15, 15), Optional.of(15), "Equal numbers"),
                Arguments.of(List.of(10, 5, 10, 3), Optional.of(10),
                        "Max value appears multiple times")
        );
    }
}
