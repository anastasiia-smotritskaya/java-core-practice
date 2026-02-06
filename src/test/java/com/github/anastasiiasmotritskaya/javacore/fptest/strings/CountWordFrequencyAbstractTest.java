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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Абстрактный тестовый класс для всех реализаций countWordFrequency
 * Наследники должны реализовать countWordFrequency()
 *
 * @see StringUtils#countWordFrequency_for(List)
 * @see StringUtils#countWordFrequency_stream(List)
 * @see CountWordFrequencyForTest
 * @see CountWordFrequencyStreamTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Counting word frequency in a list of words")
public abstract class CountWordFrequencyAbstractTest {
    protected abstract Map<String, Long> countWordFrequency(List<String> words);

    @ParameterizedTest
    @NullSource
    @DisplayName("countWordFrequency should throw IllegalArgumentException if the list is null")
    public void countWordFrequency_NullSourceTest(List<String> words) {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> countWordFrequency(words));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("countWordFrequency should throw IllegalArgumentException if one of the words is null")
    public void countWordFrequency_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> countWordFrequency(Stream.of("cat", null, "dog").collect(Collectors.toList())));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("countWordFrequency should return the proper map with various parameters")
    @MethodSource("countWordFrequencyDataProvider")
    public void countWordFrequencyTest(List<String> words, Map<String, Long> expected, String description) {
        assertEquals(expected, countWordFrequency(words));
    }

    private static Stream<Arguments> countWordFrequencyDataProvider() {
        Map<String, Long> aMap = new HashMap<>();
        aMap.put("a", 1L);

        Map<String, Long> variousParametersMap = new HashMap<>();
        variousParametersMap.put("cat", 2L);
        variousParametersMap.put("@dog", 1L);
        variousParametersMap.put("guinea pig", 1L);
        variousParametersMap.put("1pig", 1L);

        return Stream.of(
                Arguments.of(List.of(), Map.of(), "Empty list"),
                Arguments.of(List.of(""), Map.of(), "List with one empty string"),
                Arguments.of(List.of("a"), aMap, "List with one letter in lower case"),
                Arguments.of(List.of("A"), aMap, "List with one letter in upper case"),
                Arguments.of(List.of("   cat", "@dog   ", "CAT", "   guinea pig   ", "1pig"), variousParametersMap,
                        "List of various words special characters, numbers and spaces")
        );
    }
}
