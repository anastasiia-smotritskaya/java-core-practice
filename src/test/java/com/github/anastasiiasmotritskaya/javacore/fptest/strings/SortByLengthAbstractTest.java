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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций sortByLength
 * Наследники должны реализовать sortByLength()
 *
 * @see StringUtils#sortByLength_for(List)
 * @see StringUtils#sortByLength_stream(List)
 * @see SortByLengthForTest
 * @see SortByLengthStreamTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Sorting by length")
public abstract class SortByLengthAbstractTest {
    protected abstract List<String> sortByLength(List<String> strings);

    @Test
    @DisplayName("sortByLength should throw IllegalArgumentException if strings is null")
    public void sortByLength_NullSourceTest() {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sortByLength(null));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("sortByLength should throw IllegalArgumentException if one of the strings is null")
    public void sortByLength_NullStringTest() {
        List<String> strings = new ArrayList<>();
        strings.add("cat");
        strings.add(null);
        strings.add("hamster");
        strings.add("dog");

        String expected = String.format("Element at index %d must not be null", 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sortByLength(strings));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("sortByLength should return a proper list of strings with various parameters")
    @MethodSource("sortByLengthDataProvider")
    public void sortByLengthTest(List<String> strings, List<String> expected, String description) {
        assertEquals(expected, sortByLength(strings));
    }

    private static Stream<Arguments> sortByLengthDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), List.of(), "Empty list"),
                Arguments.of(List.of(""), List.of(""), "Empty string"),
                Arguments.of(List.of("   "), List.of("   "), "String with spaces"),
                Arguments.of(List.of("cat"), List.of("cat"), "One word list"),
                Arguments.of(List.of("cat", "dog", "pig", "cat"), List.of("cat", "dog", "pig", "cat"),
                        "List of words of the same length"),
                Arguments.of(List.of("guinea pig", "cat", "dog", "pig", "hamster"),
                        List.of("cat", "dog", "pig", "hamster", "guinea pig"), "List of words of different length")
        );
    }
}
