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

    @Test
    public void bubbleSortIllegalArgumentsTest() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.bubbleSort(null));
                    assertEquals("The array must not be null.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.bubbleSort(new int[]{}));
                    assertEquals("The array must not be empty.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest
    @MethodSource("bubbleSortDataProvider")
    public void bubbleSortTest(int[] expected, int[] actual) {
        assertArrayEquals(expected, ArrayUtils.bubbleSort(actual));
    }

    static Stream<Arguments> bubbleSortDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{3, 5, 2, 4, 1}, "Сортировка положительных чисел"),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}, "Сортировка в обратном порядке"),
                Arguments.of(new int[]{-5, -4, -3, -2, -1}, new int[]{-3, -5, -2, -4, -1}, "Сортировка отрицательных чисел"),
                Arguments.of(new int[]{-30, -20, -10, 10, 20}, new int[]{-20, 20, -30, -10, 10}, "Сортировка положительных и отрицательных чисел"),
                Arguments.of(new int[]{-30, -20, -10, 0, 20}, new int[]{-20, 20, -30, -10, 0}, "Сортировка чисел, среди которых есть 0"),
                Arguments.of(new int[]{1, 5, 10, 10, 20}, new int[]{20, 10, 1, 5, 10}, "Сортировка чисел, среди которых есть повторяющиеся"),
                Arguments.of(new int[]{1}, new int[]{1}, "Сортировка массива из одного числа"),
                Arguments.of(new int[]{0}, new int[]{0}, "Сортировка массива из одного нуля"),
                Arguments.of(new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, "Сортировка массива из нескольких нулей"),
                Arguments.of(new int[]{6, 6, 6, 6, 6}, new int[]{6, 6, 6, 6, 6}, "Сортировка массива из нескольких повторяющихся чисел"),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}, "Сортировка уже отсортированного массива"),
                Arguments.of(new int[]{Integer.MIN_VALUE, 1, 2, 3, 4, 5}, new int[]{3, 5, Integer.MIN_VALUE, 2, 4, 1}, "Сортировка массива с Integer.MIN_VALUE"),
                Arguments.of(new int[]{1, 2, 3, 4, 5, Integer.MAX_VALUE}, new int[]{3, 5, Integer.MAX_VALUE, 2, 4, 1}, "Сортировка массива с Integer.MAX_VALUE")
        );
    }

    @Test
    public void findInMatrixIllegalArgumentTest() {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findInMatrix(null, 6));
                    assertEquals("Matrix cannot be null.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findInMatrix(new int[0][0], 6));
                    assertEquals("Matrix cannot be empty (0 rows).", exception.getMessage());
                }
        );
    }

    @ParameterizedTest
    @MethodSource("findInMatrixPositiveDataProvider")
    public void findInMatrixPositiveTest(int[] expected, int[][] matrix, int target) {
        assertArrayEquals(expected, ArrayUtils.findInMatrix(matrix, target));
    }

    static Stream<Arguments> findInMatrixPositiveDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 4,
                        "Поиск числа в матрице из положительных чисел"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 4,
                        "Поиск числа в матрице, где одна строка - null"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}, {10, 11, 12}}, 4,
                        "Поиск числа в матрице, где есть ноль"),
                Arguments.of(new int[]{1, 1}, new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}, {10, 11, 12}}, 0,
                        "Поиск нуля в матрице"),
                Arguments.of(new int[]{0, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 1,
                        "Поиск числа в матрице из положительных чисел число является первым в матрице"),
                Arguments.of(new int[]{2, 2}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 9,
                        "Поиск числа в матрице из положительных чисел число является последним в матрице"),
                Arguments.of(new int[]{1, 0}, new int[][]{{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}, {-10, -11, -12}}, -4,
                        "Поиск числа в матрице из отрицательных чисел"),
                Arguments.of(new int[]{0, 0}, new int[][]{{5}}, 5, "Поиск числа в матрице из одного числа"),
                Arguments.of(new int[]{0, 0}, new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, "Поиск нуля в матрице из нулей"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 4, 6}, {4, 8, 9}, {10, 11, 12}}, 4,
                        "Поиск числа в матрице, где таких чисел несколько (только первое вхождение)"),
                Arguments.of(new int[]{0, 0}, new int[][]{{1, 2, 3}, {}, {7, 8, 9}}, 1,
                        "Поиск числа в матрице, где одна из строк - пустая({})")
        );
    }

    @ParameterizedTest
    @MethodSource("findInMatrixNegativeDataProvider")
    public void findInMatrixNegativeTest(int[][] matrix, int target) {
        assertArrayEquals(new int[]{-1, -1}, ArrayUtils.findInMatrix(matrix, target));
    }

    static Stream<Arguments> findInMatrixNegativeDataProvider() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 345,
                        "Поиск положительного числа, которого нет в матрице"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 345,
                        "Поиск положительного числа, которого нет в матрице, но одна из строк матрицы - null"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 0,
                        "Поиск нуля, которого нет в матрице, но одна из строк матрицы - null"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, -2,
                        "Поиск отрицательного числа, которого нет в матрице, но есть равное по модулю положительное"),
                Arguments.of(new int[][]{{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}, {-10, -11, -12}}, 2,
                        "Поиск положительного числа, которого нет в матрице, но есть равное по модулю отрицательное"),
                Arguments.of(new int[][]{{1, 20, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 2,
                        "Поиск числа, которого нет в матрице, но это число есть в виду цифры"),
                Arguments.of(new int[][]{{50}}, 5, "Поиск числа в матрице из одного числа"),
                Arguments.of(new int[][]{null, null, null}, 5, "Поиск числа в матрице, где все строки - null"),
                Arguments.of(new int[][]{{1, 2, 3}, {}, {7, 8, 9}}, 5, "Поиск числа в матрице, где одна строка - пустая ({})")
        );
    }

    @Test
    public void findInMatrixJaggedTest() {
        int[][] jagged = {
                {1, 2},
                {3, 4, 5, 6},
                {7},
                {8, 9, 10}
        };
        assertAll(
                () -> assertArrayEquals(new int[]{1, 3}, ArrayUtils.findInMatrix(jagged, 6)),
                () -> assertArrayEquals(new int[]{2, 0}, ArrayUtils.findInMatrix(jagged, 7)),
                () -> assertArrayEquals(new int[]{-1, -1}, ArrayUtils.findInMatrix(jagged, 11))
        );
    }
}
