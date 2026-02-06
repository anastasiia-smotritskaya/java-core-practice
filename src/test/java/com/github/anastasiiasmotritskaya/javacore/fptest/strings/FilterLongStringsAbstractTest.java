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
 * Абстрактный тестовый класс для всех реализаций filterLongStrings
 * Наследники должны реализовать filterLongStrings()
 *
 * @see StringUtils#filterLongStrings_for(List, int)
 * @see StringUtils#filterLongStrings_lambda(List, int)
 * @see StringUtils#filterLongStrings_mr(List, int)
 * @see FilterLongStringsForTest
 * @see FilterLongStringsLambdaTest
 * @see FilterLongStringsMRTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Filtering long strings")
public abstract class FilterLongStringsAbstractTest {
    protected abstract List<String> filterLongStrings(List<String> strings, int minLength);

    @ParameterizedTest
    @NullSource
    @DisplayName("filterLongStrings should throw IllegalArgumentException if the list of strings is null")
    public void filterLongStrings_NullSourceTest(List<String> strings) {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> filterLongStrings(strings, 5));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("filterLongStrings should throw IllegalArgumentException if one of the strings in the list is null")
    public void filterLongStrings_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add(null);
        strings.add("seventeen");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> filterLongStrings(strings, 5));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {3} minLength: {1}")
    @DisplayName("filterLongStrings tests with various parameters")
    @MethodSource("filterLongStringsDataProvider")
    public void filterLongStrings(List<String> strings, int minLength, List<String> expected, String description) {
        assertEquals(expected, filterLongStrings(strings, minLength));
    }

    private static Stream<Arguments> filterLongStringsDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), 5, List.of(), "Empty list"),

                Arguments.of(List.of(""), 5, List.of(), "One empty string"),

                Arguments.of(List.of(""), 0, List.of(), "One empty string"),

                Arguments.of(List.of(" "), 0, List.of(), "One space string"),

                Arguments.of(List.of("   "), 0, List.of(), "Few spaces string"),

                Arguments.of(List.of("", "", ""), 5, List.of(), "Few empty strings"),

                Arguments.of(List.of("", "", ""), 0, List.of(), "Few empty strings"),

                Arguments.of(List.of(" ", " ", " "), 0, List.of(), "Few strings with one space"),

                Arguments.of(List.of("   ", "   ", "   "), 0, List.of(), "Few strings with a few spaces"),

                Arguments.of(List.of("string 1", "string 2", "string 3"), 0,
                        List.of("string 1", "string 2", "string 3"), "Three lines of equal length = 8"),

                Arguments.of(List.of("string 1", "string 2", "string 3"), 1,
                        List.of("string 1", "string 2", "string 3"), "Three lines of equal length = 8"),

                Arguments.of(List.of("string 1", "string 2", "string 3"), 7,
                        List.of("string 1", "string 2", "string 3"), "Three lines of equal length = 8"),

                Arguments.of(List.of("string 1", "string 2", "string 3"), 8,
                        List.of(), "Three lines of equal length = 8"),

                Arguments.of(List.of("string 1", "string 2", "string 3"), 9,
                        List.of(), "Three lines of equal length = 8"),

                Arguments.of(List.of("string 1", "string 22", "string 333"), 7,
                        List.of("string 1", "string 22", "string 333"), "Three lines of different lengths: 8, 9, 10"),

                Arguments.of(List.of("string 1", "string 22", "string 333"), 8,
                        List.of("string 22", "string 333"), "Three lines of different lengths: 8, 9, 10"),

                Arguments.of(List.of("string 1", "string 22", "string 333"), 9,
                        List.of("string 333"), "Three lines of different lengths: 8, 9, 10"),

                Arguments.of(List.of("string 1", "string 22", "string 333"), 10,
                        List.of(), "Three lines of different lengths: 8, 9, 10"),

                Arguments.of(List.of("string 1", "string 22", "string 333"), 11,
                        List.of(), "Three lines of different lengths: 8, 9, 10"),

                Arguments.of(List.of("   string"), 4, List.of("string"), "Few spaces in the beginning"),

                Arguments.of(List.of("   string"), 5, List.of("string"), "Few spaces in the beginning"),

                Arguments.of(List.of("   string"), 6, List.of(), "Few spaces in the beginning"),

                Arguments.of(List.of("   string"), 7, List.of(), "Few spaces in the beginning"),

                Arguments.of(List.of("   string"), 8, List.of(), "Few spaces in the beginning"),

                Arguments.of(List.of("string   "), 4, List.of("string"), "Few spaces in the end"),

                Arguments.of(List.of("string   "), 5, List.of("string"), "Few spaces in the end"),

                Arguments.of(List.of("string   "), 6, List.of(), "Few spaces in the end"),

                Arguments.of(List.of("string   "), 7, List.of(), "Few spaces in the end"),

                Arguments.of(List.of("string   "), 8, List.of(), "Few spaces in the end"),

                Arguments.of(List.of("string   string", "string string"), 11,
                        List.of("string   string", "string string"), "Few spaces in the middle"),

                Arguments.of(List.of("string   string", "string string"), 12,
                        List.of("string   string", "string string"), "Few spaces in the middle"),

                Arguments.of(List.of("string   string", "string string"), 13,
                        List.of("string   string"), "Few spaces in the middle"),

                Arguments.of(List.of("string   string", "string string"), 14,
                        List.of("string   string"), "Few spaces in the middle"),

                Arguments.of(List.of("string   string", "string string"), 15,
                        List.of(), "Few spaces in the middle"),

                Arguments.of(List.of("string   string", "string string"), 16,
                        List.of(), "Few spaces in the middle"),

                Arguments.of(List.of("string 1", "", "string 2", "", "string 3"), 0,
                        List.of("string 1", "string 2", "string 3"), "Few empty strings in the list"),

                Arguments.of(List.of("string 1", "", "string 2", "", "string 3"), 1,
                        List.of("string 1", "string 2", "string 3"), "Few empty strings in the list"),

                Arguments.of(List.of("string 1", "", "string 2", "", "string 3"), 7,
                        List.of("string 1", "string 2", "string 3"), "Few empty strings in the list"),

                Arguments.of(List.of("string 1", "", "string 2", "", "string 3"), 8,
                        List.of(), "Few empty strings in the list"),

                Arguments.of(List.of("string 1", "", "string 2", "", "string 3"), 9,
                        List.of(), "Few empty strings in the list")
        );
    }
}
