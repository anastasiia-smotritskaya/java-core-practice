package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsTest {

    @ParameterizedTest
    @MethodSource("findMinMaxDataProvider")
    public void findMinMaxTest(int[] expectedArray, int[] array) {
        assertArrayEquals(expectedArray, ArrayUtils.findMinMax(array));
    }

    static Stream<Arguments> findMinMaxDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 9}, new int[]{5, 2, 8, 1, 9}, "Массив из положительных чисел"),
                Arguments.of(new int[]{-10, 10}, new int[]{-10, 0, 10}, "Массив из положительных и отрицательных чисел"),
                Arguments.of(new int[]{1, 5}, new int[]{1, 2, 3, 4, 5}, "Уже отсортированный массив"),
                Arguments.of(new int[]{-5, -1}, new int[]{-1, -5, -3, -2}, "Массив из отрицательных чисел"),
                Arguments.of(new int[]{0, 4}, new int[]{0, 1, 2, 3, 4}, "В массиве есть ноль"),
                Arguments.of(new int[]{0, 4}, new int[]{0, 1, 2, 0, 4}, "В массиве есть несколько нулей"),
                Arguments.of(new int[]{0, 0}, new int[]{0, 0, 0, 0, 0}, "Массив состоит из нескольких нулей"),
                Arguments.of(new int[]{0, 0}, new int[]{0}, "Массив состоит из одного нуля"),
                Arguments.of(new int[]{1, 1}, new int[]{1}, "Массив состоит из одного положительного значения"),
                Arguments.of(new int[]{6, 6}, new int[]{6, 6, 6, 6, 6}, "Массив состоит из одинаковых положительных чисел"),
                Arguments.of(new int[]{-6, -6}, new int[]{-6, -6, -6, -6, -6}, "Массив состоит из одинаковых отрицательных чисел"),
                Arguments.of(new int[]{-6, -6}, new int[]{-6}, "Массив состоит из одного отрицательного значения"),
                Arguments.of(new int[]{Integer.MIN_VALUE, 5}, new int[]{5, 4, 3, Integer.MIN_VALUE, 2, 1}, "Массив содержит Integer.MIN_VALUE"),
                Arguments.of(new int[]{1, Integer.MAX_VALUE}, new int[]{3, 4, 1, Integer.MAX_VALUE, 2, 1}, "Массив содержит Integer.MAX_VALUE"),
                Arguments.of(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, "Массив состоит из Integer.MAX_VALUE"),
                Arguments.of(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, "Массив состоит из Integer.MIN_VALUE")
        );
    }

    @Test
    public void findMinMaxIllegalArgumentsTest() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findMinMax(null));
                    assertEquals("The array must not be null.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findMinMax(new int[]{}));
                    assertEquals("The array must not be empty.", exception.getMessage());
                }
        );
    }
}
