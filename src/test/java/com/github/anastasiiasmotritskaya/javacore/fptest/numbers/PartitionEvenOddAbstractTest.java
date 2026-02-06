package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций partitionEvenOdd
 * Наследники должны реализовать partitionEvenOdd()
 *
 * @see NumberUtils#partitionEvenOdd_for(List)
 * @see PartitionEvenOddForTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Dividing numbers into even and odd")
public abstract class PartitionEvenOddAbstractTest {
    protected abstract Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers);

    @ParameterizedTest
    @NullSource
    @DisplayName("partitionEvenOdd should throw IllegalArgumentException if the list of numbers is null")
    public void partitionEvenOdd_NullSourceTest(List<Integer> numbers) {
        String expected = "List of numbers must not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> partitionEvenOdd(numbers));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("partitionEvenOdd should return the proper map with various parameters")
    @MethodSource("partitionEvenOddDataProvider")
    public void partitionEvenOddTest(List<Integer> numbers, Map<Boolean, List<Integer>> expected, String description) {
        assertEquals(expected, partitionEvenOdd(numbers));
    }

    private static Stream<Arguments> partitionEvenOddDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), Map.of(true, List.of(), false, List.of()), "Empty list"),

                Arguments.of(List.of(0),
                        Map.of(true, new ArrayList<>(List.of(0)), false, List.of()),
                        "Only one zero in the list"),

                Arguments.of(List.of(1),
                        Map.of(true, List.of(), false, new ArrayList<>(List.of(1))),
                        "Only one odd number in the list"),

                Arguments.of(List.of(2),
                        Map.of(true, new ArrayList<>(List.of(2)), false, List.of()),
                        "Only one even number in the list"),

                Arguments.of(List.of(-33, -4, 0, 4, 33),
                        Map.of(true, new ArrayList<>(List.of(-4, 0, 4)), false, new ArrayList<>(List.of(-33, 33))),
                        "Mixed numbers list"),

                Arguments.of(List.of(33, 4, 4, 33),
                        Map.of(true, new ArrayList<>(List.of(4, 4)), false, new ArrayList<>(List.of(33, 33))),
                        "Repeating numbers list")
        );
    }
}
