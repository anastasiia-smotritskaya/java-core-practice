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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Абстрактный тестовый класс для всех реализаций groupByFirstLetter
 * Наследники должны реализовать groupByFirstLetter()
 *
 * @see StringUtils#groupByFirstLetter_for(List)
 * @see StringUtils#groupByFirstLetter_stream(List)
 * @see GroupByFirstLetterForTest
 * @see GroupByFirstLetterStreamTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Grouping by first letter")
public abstract class GroupByFirstLetterAbstractTest {
    protected abstract Map<Character, List<String>> groupByFirstLetter(List<String> strings);

    @Test
    @DisplayName("groupByFirstLetter should throw IllegalArgumentException if the list is null")
    public void groupByFirstLetter_NullSourceTest() {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> groupByFirstLetter(null));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("groupByFirstLetter should throw IllegalArgumentException one of the strings in the list is null")
    public void groupByFirstLetter_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> groupByFirstLetter(Stream.of("one", null, "three").collect(Collectors.toList())));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @DisplayName("groupByFirstLetter should return a proper map for different lists")
    @MethodSource("groupByFirstLetterDataProvider")
    public void groupByFirstLetterTest(List<String> strings, Map<Character, List<String>> expected, String description) {
        Map<Character, List<String>> actual = groupByFirstLetter(strings);

        assertEquals(expected, actual);
    }

    static private Stream<Arguments> groupByFirstLetterDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), Map.of(), "Empty list"),

                Arguments.of(List.of(""), Map.of(), "List with one empty string"),

                Arguments.of(List.of(" "), Map.of(' ', List.of(" ")),
                        "List with one string with space"),

                Arguments.of(List.of("a"), Map.of('a', List.of("a")),
                        "List with string with one letter"),

                Arguments.of(List.of("a", "b"), Map.of('a', List.of("a"), 'b', List.of("b")),
                        "List with string with one letter"),

                Arguments.of(List.of("cat", "dog", "cow"),
                        Map.of('c', List.of("cat", "cow"), 'd', List.of("dog")),
                        "List has several words with equal first letter"),

                Arguments.of(List.of(" cat", "dog", "cow"),
                        Map.of(' ', List.of(" cat"), 'd', List.of("dog"), 'c', List.of("cow")),
                        "Space in one word"),

                Arguments.of(List.of(" cat", " dog", " cow"),
                        Map.of(' ', List.of(" cat", " dog", " cow")), "Space in several words"),

                Arguments.of(List.of("cat", "dog", "Cow"),
                        Map.of('c', List.of("cat", "Cow"), 'd', List.of("dog")),
                        "Lower case and upper case as first letter in the list"),

                Arguments.of(List.of("@cat", "@dog", "!cow"),
                        Map.of('@', List.of("@cat", "@dog"), '!', List.of("!cow")),
                        "Special characters as first letter"),

                Arguments.of(List.of("1cat", "2dog", "2cow"),
                        Map.of('1', List.of("1cat"), '2', List.of("2dog", "2cow")),
                        "Numbers as first letter")
        );
    }
}
