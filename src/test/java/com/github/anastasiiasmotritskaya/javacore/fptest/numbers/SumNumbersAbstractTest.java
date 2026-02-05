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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций sumNumbers
 * Наследники должны реализовать sumNumbers()
 *
 * @see NumberUtils#sumNumbers_for(List)
 * @see NumberUtils#sumNumbers_lambda(List)
 * @see SumNumbersForLoopTest
 * @see SumNumbersLambdaTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Summation of numbers")
public abstract class SumNumbersAbstractTest {

    protected abstract int sumNumbers(List<Integer> numbers);

    @ParameterizedTest
    @NullSource
    @DisplayName("sumNumbers should throw IllegalArgumentException if the list is null")
    public void sumNumbers_NullSourceTest(List<Integer> numbers) {
        String expected = "List of numbers must not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sumNumbers(numbers));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("sumNumbers should return the sum of numbers if the list contains different types of numbers")
    @MethodSource("sumNumbersDataProvider")
    public void sumNumbersTest(List<Integer> numbers, int expected, String description) {
        assertEquals(expected, sumNumbers(numbers));
    }

    private static Stream<Arguments> sumNumbersDataProvider() {
        return Stream.of(
                Arguments.of(new ArrayList<>(), 0, "Empty list"),
                Arguments.of(List.of(0), 0, "Only zero list"),
                Arguments.of(List.of(1, 10, 100), 111, "Only positive numbers list"),
                Arguments.of(List.of(-1, -10, -100), -111, "Only negative numbers list"),
                Arguments.of(List.of(-1, 2, -10, 0, 20, -100, 200), 111, "Mixed numbers list, sum is not null"),
                Arguments.of(List.of(-50, 25, 25, 75, -25, -25, -25), 0, "Mixed numbers list, sum is null")
        );
    }
}
