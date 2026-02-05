package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
 * Абстрактный тестовый класс для всех реализаций toUpperCase
 * Наследники должны реализовать toUpperCase()
 *
 * @see StringUtils#toUpperCaseTrimmed_for(List)
 * @see StringUtils#toUpperCaseTrimmed_stream(List)
 * @see ToUpperCaseTrimmedForTest
 * @see ToUpperCaseTrimmedStreamTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Converting a list of strings to uppercase")
public abstract class ToUpperCaseTrimmedAbstractTest {
    protected abstract List<String> toUpperCaseTrimmed(List<String> strings);

    @ParameterizedTest
    @NullSource
    @DisplayName("toUpperCaseTrimmed should throw IllegalArgumentException if the list is null")
    public void toUpperCaseTrimmed_NullSourceTest(List<String> strings) {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> toUpperCaseTrimmed(strings));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("toUpperCaseTrimmed should throw NullPointerException if one of the strings is null")
    public void toUpperCaseTrimmed_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add(null);
        strings.add("seventeen");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> toUpperCaseTrimmed(strings));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("toUpperCaseTrimmed should return the proper list of strings for various parameters")
    @MethodSource("toUpperCaseTrimmedDataProvider")
    public void toUpperCaseTrimmedTest(List<String> strings, List<String> expected, String description) {
        assertEquals(expected, toUpperCaseTrimmed(strings));
    }

    private static Stream<Arguments> toUpperCaseTrimmedDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), List.of(), "Completely empty list"),
                Arguments.of(List.of(""), List.of(""), "List with one empty string"),
                Arguments.of(List.of("   "), List.of(""), "List with one string with spaces"),
                Arguments.of(List.of("one", "two", "three"), List.of("ONE", "TWO", "THREE"),
                        "Lower case list"),
                Arguments.of(List.of("One", "Two", "Three"), List.of("ONE", "TWO", "THREE"),
                        "Mixed case list"),
                Arguments.of(List.of("ONE", "TWO", "THREE"), List.of("ONE", "TWO", "THREE"),
                        "Upper case list"),
                Arguments.of(List.of("one", "", "three"), List.of("ONE", "", "THREE"),
                        "List has an empty string"),
                Arguments.of(List.of("  one   "), List.of("ONE"),
                        "Spaces in the beginning and in the end")
        );
    }
}
