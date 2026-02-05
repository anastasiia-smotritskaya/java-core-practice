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
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций filterLongStrings
 * Наследники должны реализовать filterLongStrings()
 *
 * @see StringUtils#containsSubstring_for(List, String)
 * @see StringUtils#containsSubstring_stream(List, String)
 * @see ContainsSubstringForTest
 * @see ContainsSubstringStreamTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Finding a substring in a list of strings")
public abstract class ContainsSubstringAbstractTest {
    protected abstract boolean containsSubstring(List<String> strings, String substring);

    @Test
    @DisplayName("containsSubstring should throw IllegalArgumentException if list of strings is null")
    public void containsSubstring_NullSourceTest() {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> containsSubstring(null, "pig"));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("containsSubstring should throw IllegalArgumentException if one of the strings in the list is null")
    public void containsSubstring_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> containsSubstring(Stream.of("cat", null, "dog", "guinea pig")
                        .collect(Collectors.toList()), "pig"));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("containsSubstring should throw IllegalArgumentException if the substring is null")
    public void containsSubstring_NullSubstringTest(String substring) {
        String expected = "Substring should not be null or empty.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> containsSubstring(Stream.of("cat", "dog", "guinea pig")
                        .collect(Collectors.toList()), substring));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @DisplayName("containsSubstring should return correct result with various parameters")
    @MethodSource("containsSubstringDataProvider")
    public void containsSubstringTest(List<String> strings, String substring, boolean result, String description) {
        assertEquals(result, containsSubstring(strings, substring));
    }

    private static Stream<Arguments> containsSubstringDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), "pig", false, "Empty list"),
                Arguments.of(List.of(""), "pig", false, "List with one empty string"),
                Arguments.of(List.of("a"), "a", true, "One letter list and equal one letter substring"),
                Arguments.of(List.of("b"), "a", false,
                        "One letter list and different one letter substring"),
                Arguments.of(List.of("cat"), "a", true, "One word list and one letter substring"),
                Arguments.of(List.of("cat", "dog", "pig"), "g", true,
                        "Several words list and one letter substring"),
                Arguments.of(List.of("cat", "dog", "pig"), "pig", true,
                        "Several words list and one word substring"),
                Arguments.of(List.of("cat", "guinea pig", "dog", "pig"), "pig", true,
                        "Several words list and one word substring"),
                Arguments.of(List.of("cat", "guinea pig", "dog", "pig"), " ", true,
                        "Space as a substring"),
                Arguments.of(List.of("cat", "guinea pig", "dog"), "cow", false,
                        "There are no such substring in the list"),
                Arguments.of(List.of("CAT", "DOG", "PIG"), "pig", true,
                        "Words in the list in upper case, substring is in lower case"),
                Arguments.of(List.of("cat", "dog", "pig"), "PIG", true,
                        "Words in the list in lower case, substring is in upper case")
        );
    }
}
