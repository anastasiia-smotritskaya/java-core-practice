package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
 * Абстрактный тестовый класс для всех реализаций doubleNumbers
 * Наследники должны реализовать doubleNumbers()
 *
 * @see NumberUtils#doubleNumbers_for(List)
 * @see NumberUtils#doubleNumbers_lambda(List)
 * @see NumberUtils#doubleNumbers_mr(List)
 * @see DoubleNumbersForLoopTest
 * @see DoubleNumbersLambdaTest
 * @see DoubleNumbersMRTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Doubling numbers")
public abstract class DoubleNumbersAbstractTest {
    protected abstract List<Integer> doubleNumbers(List<Integer> numbers);

    @Test
    @DisplayName("doubleNumbers should throw IllegalArgumentException if the list of numbers is null")
    public void doubleNumbers_NullSourceTest() {
        String expected = "List of numbers must not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> doubleNumbers(null));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("doubleNumbers should return a list of doubled numbers")
    @MethodSource("doubleNumbersDataProvider")
    public void doubleNumbersTest(List<Integer> numbers, List<Integer> expected, String description) {
        List<Integer> actual = doubleNumbers(numbers);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> doubleNumbersDataProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), List.of(2, 4, 6, 8, 10),
                        "Positive numbers test"),
                Arguments.of(List.of(-10, -5, 0, 5, 10), List.of(-20, -10, 0, 10, 20),
                        "Mixed numbers test with zero"),
                Arguments.of(new ArrayList<>(), new ArrayList<>(),
                        "Empty list")
        );
    }
}
