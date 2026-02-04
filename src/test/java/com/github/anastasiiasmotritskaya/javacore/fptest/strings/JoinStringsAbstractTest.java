package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
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
 * Абстрактный тестовый класс для всех реализаций joinStrings
 * Наследники должны реализовать joinStrings()
 *
 * @see StringUtils#joinStrings_for(List, String)
 * @see StringUtils#joinStrings_stream(List, String)
 * @see JoinStringsForTest
 * @see JoinStringsStreamTest
 */
public abstract class JoinStringsAbstractTest {
    protected abstract String joinStrings(List<String> strings, String delimiter);

    @Test
    @DisplayName("joinStrings should throw IllegalArgumentException if the list is null")
    public void joinStrings_NullSourceTest() {
        String expected = "List of strings should not be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> joinStrings(null, "@"));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("joinStrings should throw NullPointerException if one of the strings is null")
    public void joinStrings_NullStringTest() {
        String expected = String.format("Element at index %d must not be null", 1);

        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add(null);
        strings.add("three");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> joinStrings(strings, "@"));

        assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest()
    @DisplayName("joinStrings should return the proper string if there are various parameters in the method")
    @MethodSource("joinStringsDataProvider")
    public void joinStringsTest(List<String> strings, String delimiter, String expected, String description) {
        assertEquals(expected, joinStrings(strings, delimiter));
    }

    private static Stream<Arguments> joinStringsDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), "@", "", "Completely empty list"),
                Arguments.of(List.of(""), "@", "", "One empty string list"),
                Arguments.of(List.of("   "), "@", "", "One string with spaces list"),
                Arguments.of(List.of("one", "two", "three"), "@", "one@two@three",
                        "Plain positive test with character delimeter"),
                Arguments.of(List.of("one", "two", "three"), " ", "one two three",
                        "Plain positive test with space delimeter"),
                Arguments.of(List.of("   one   ", "   two   ", "   three   "), "A", "oneAtwoAthree",
                        "Strings have spaces in the end and in the beginning")
        );
    }
}
