package com.github.anastasiiasmotritskaya.javacore.datatest;

import com.github.anastasiiasmotritskaya.javacore.data.ArrayUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Java Core")
@Feature("Working with data")
@Story("Arrays")
public class ArrayUtilsTest {

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("findMinMaxDataProvider")
    @DisplayName("findMinMax with various input arrays")
    public void findMinMaxTest(int[] expectedArray, int[] array, String description) {
        Assertions.assertArrayEquals(expectedArray, ArrayUtils.findMinMax(array));
    }

    static Stream<Arguments> findMinMaxDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 9}, new int[]{5, 2, 8, 1, 9}, "An array of positive numbers"),
                Arguments.of(new int[]{-10, 10}, new int[]{-10, 0, 10}, "An array of positive and negative numbers"),
                Arguments.of(new int[]{1, 5}, new int[]{1, 2, 3, 4, 5}, "Already sorted array"),
                Arguments.of(new int[]{-5, -1}, new int[]{-1, -5, -3, -2}, "Array of negative numbers"),
                Arguments.of(new int[]{0, 4}, new int[]{0, 1, 2, 3, 4}, "There is a zero in the array"),
                Arguments.of(new int[]{0, 4}, new int[]{0, 1, 2, 0, 4}, "There are multiple zeros in the array"),
                Arguments.of(new int[]{0, 0}, new int[]{0, 0, 0, 0, 0}, "The array consists of several zeros"),
                Arguments.of(new int[]{0, 0}, new int[]{0}, "The array consists of one zero"),
                Arguments.of(new int[]{1, 1}, new int[]{1}, "The array consists of one positive value"),
                Arguments.of(new int[]{6, 6}, new int[]{6, 6, 6, 6, 6}, "The array consists of identical positive numbers"),
                Arguments.of(new int[]{-6, -6}, new int[]{-6, -6, -6, -6, -6}, "The array consists of identical negative numbers"),
                Arguments.of(new int[]{-6, -6}, new int[]{-6}, "The array consists of one negative value"),
                Arguments.of(new int[]{Integer.MIN_VALUE, 5}, new int[]{5, 4, 3, Integer.MIN_VALUE, 2, 1}, "There is an Integer.MIN_VALUE in the array"),
                Arguments.of(new int[]{1, Integer.MAX_VALUE}, new int[]{3, 4, 1, Integer.MAX_VALUE, 2, 1}, "There is an Integer.MAX_VALUE in the array"),
                Arguments.of(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, "The array consists of Integer.MAX_VALUE"),
                Arguments.of(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, "The array consists of Integer.MIN_VALUE")
        );
    }

    @Test
    @DisplayName("findMinMax should throw IllegalArgumentException for null array")
    public void findMinMaxTest_nullArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findMinMax(null));

        assertEquals("The array must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("findMinMax should throw IllegalArgumentException for empty array")
    public void findMinMaxTest_emptyArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findMinMax(new int[]{}));

        assertEquals("The array must not be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("bubbleSort should throw IllegalArgumentException for null array")
    public void bubbleSortTest_nullArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.bubbleSort(null));

        assertEquals("The array must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("bubbleSort should throw IllegalArgumentException for empty array")
    public void bubbleSortTest_emptyArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.bubbleSort(new int[]{}));

        assertEquals("The array must not be empty.", exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("bubbleSortDataProvider")
    @DisplayName("bubbleSort with positive results")
    public void bubbleSortTest(int[] expected, int[] actual, String description) {
        assertArrayEquals(expected, ArrayUtils.bubbleSort(actual));
    }

    static Stream<Arguments> bubbleSortDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{3, 5, 2, 4, 1}, "Sorting positive numbers"),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}, "Sorting in reverse order"),
                Arguments.of(new int[]{-5, -4, -3, -2, -1}, new int[]{-3, -5, -2, -4, -1}, "Sorting negative numbers"),
                Arguments.of(new int[]{-30, -20, -10, 10, 20}, new int[]{-20, 20, -30, -10, 10}, "Sorting positive and negative numbers"),
                Arguments.of(new int[]{-30, -20, -10, 0, 20}, new int[]{-20, 20, -30, -10, 0}, "Sorting numbers that include 0"),
                Arguments.of(new int[]{1, 5, 10, 10, 20}, new int[]{20, 10, 1, 5, 10}, "Sorting numbers that contain duplicates"),
                Arguments.of(new int[]{1}, new int[]{1}, "Sorting an array of one number"),
                Arguments.of(new int[]{0}, new int[]{0}, "Sorting an array of single zeros"),
                Arguments.of(new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, "Sorting an array of multiple zeros"),
                Arguments.of(new int[]{6, 6, 6, 6, 6}, new int[]{6, 6, 6, 6, 6}, "Sorting an array of multiple repeating numbers"),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}, "Sorting an already sorted array"),
                Arguments.of(new int[]{Integer.MIN_VALUE, 1, 2, 3, 4, 5}, new int[]{3, 5, Integer.MIN_VALUE, 2, 4, 1}, "Sorting an array with Integer.MIN_VALUE"),
                Arguments.of(new int[]{1, 2, 3, 4, 5, Integer.MAX_VALUE}, new int[]{3, 5, Integer.MAX_VALUE, 2, 4, 1}, "Sorting an array with Integer.MAX_VALUE")
        );
    }

    @Test
    @DisplayName("findInMatrix should throw IllegalArgumentException for null matrix")
    public void findInMatrixTest_nullArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findInMatrix(null, 6));

        assertEquals("Matrix cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("findInMatrix should throw IllegalArgumentException for empty (0 rows) matrix")
    public void findInMatrixTest_emptyArray_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.findInMatrix(new int[0][0], 6));

        assertEquals("Matrix cannot be empty (0 rows).", exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("findInMatrixPositiveDataProvider")
    @DisplayName("findInMatrix with positive results")
    public void findInMatrixPositiveTest(int[] expected, int[][] matrix, int target, String description) {
        assertArrayEquals(expected, ArrayUtils.findInMatrix(matrix, target));
    }

    static Stream<Arguments> findInMatrixPositiveDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 4,
                        "Finding a number in a matrix of positive numbers"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 4,
                        "Finding a number in a matrix where one row is null"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}, {10, 11, 12}}, 4,
                        "Finding a number in a matrix that contains zero"),
                Arguments.of(new int[]{1, 1}, new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}, {10, 11, 12}}, 0,
                        "Finding zero in a matrix"),
                Arguments.of(new int[]{0, 0}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 1,
                        "Finding a number in a matrix of positive numbers is the first number in the matrix"),
                Arguments.of(new int[]{2, 2}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 9,
                        "Finding a number in a matrix of positive numbers is the last number in the matrix"),
                Arguments.of(new int[]{1, 0}, new int[][]{{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}, {-10, -11, -12}}, -4,
                        "Finding a number in a matrix of negative numbers"),
                Arguments.of(new int[]{0, 0}, new int[][]{{5}}, 5, "Finding a number in a matrix of one number"),
                Arguments.of(new int[]{0, 0}, new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, "Finding a zero in a matrix of zeros"),
                Arguments.of(new int[]{1, 0}, new int[][]{{1, 2, 3}, {4, 4, 6}, {4, 8, 9}, {10, 11, 12}}, 4,
                        "Finding a number in a matrix where there are multiple such numbers (only the first occurrence)"),
                Arguments.of(new int[]{0, 0}, new int[][]{{1, 2, 3}, {}, {7, 8, 9}}, 1,
                        "Finding a number in a matrix where one of the rows is empty ({})")
        );
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("findInMatrixNegativeDataProvider")
    @DisplayName("findInMatrix with negative results")
    public void findInMatrixNegativeTest(int[][] matrix, int target, String description) {
        assertArrayEquals(new int[]{-1, -1}, ArrayUtils.findInMatrix(matrix, target));
    }

    static Stream<Arguments> findInMatrixNegativeDataProvider() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 345,
                        "Finding a positive number that is not in a matrix"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 345,
                        "Find a positive number that is not in a matrix, but one of the matrix rows is null"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, null, {10, 11, 12}}, 0,
                        "Finding a zero that is not in a matrix, but one of the matrix rows is null"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, -2,
                        "Finding a negative number that is not in the matrix, but has a positive number of equal magnitude"),
                Arguments.of(new int[][]{{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}, {-10, -11, -12}}, 2,
                        "Finding a positive number that is not in the matrix, but has a negative number of equal magnitude"),
                Arguments.of(new int[][]{{1, 20, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 2,
                        "Finding a number that is not in the matrix, but this number is in the digits"),
                Arguments.of(new int[][]{{50}}, 5, "Finding a number in a matrix of one number"),
                Arguments.of(new int[][]{null, null, null}, 5, "Finding a number in a matrix where all rows are null"),
                Arguments.of(new int[][]{{1, 2, 3}, {}, {7, 8, 9}}, 5, "Finding a number in a matrix where one row is empty ({})")
        );
    }

    @Test
    @DisplayName("findInMatrix with jagged matrix")
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

    @Test
    @DisplayName("transposeMatrix should throw IllegalArgumentException for null matrix")
    public void transposeMatrixTest_nullMatrix_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.transposeMatrix(null));

        assertEquals("Matrix cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("transposeMatrix should throw IllegalArgumentException for empty matrix")
    public void transposeMatrixTest_emptyMatrix_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.transposeMatrix(new int[][]{}));

        assertEquals("Matrix cannot be empty (0 rows).", exception.getMessage());
    }

    @Test
    @DisplayName("transposeMatrix should throw IllegalArgumentException for not rectangular matrix")
    public void transposeMatrixTest_notRectangularMatrix_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.transposeMatrix(new int[][]{{1, 2, 3}, {5, 6}, {7, 8, 9}}));

        assertEquals("The matrix must be rectangular.", exception.getMessage());
    }

    @Test
    @DisplayName("transposeMatrix should throw IllegalArgumentException for matrix with empty rows")
    public void transposeMatrixTest_emptyRowsMatrix_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.transposeMatrix(new int[][]{{}, {}, {}}));

        assertEquals("The matrix cannot have empty rows.", exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("transposeMatrixDataProvider")
    @DisplayName("transposeMatrix with various types of matrix")
    public void transposeMatrixTest(int[][] expected, int[][] matrix, String description) {
        assertArrayEquals(expected, ArrayUtils.transposeMatrix(matrix));
    }

    static Stream<Arguments> transposeMatrixDataProvider() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 4}, {2, 5}, {3, 6}}, new int[][]{{1, 2, 3}, {4, 5, 6}},
                        "Rectangular matrix: The number of columns is greater than the number of rows"),
                Arguments.of(new int[][]{{1, 2, 3}, {4, 5, 6}}, new int[][]{{1, 4}, {2, 5}, {3, 6}},
                        "Rectangular matrix: The number of rows is greater than the number of columns"),
                Arguments.of(new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        "Square matrix"),
                Arguments.of(new int[][]{{1, 4}, {2, 0}, {3, 6}}, new int[][]{{1, 2, 3}, {4, 0, 6}},
                        "Matrix containing zero"),
                Arguments.of(new int[][]{{-1, 4}, {-2, 5}, {-3, 6}}, new int[][]{{-1, -2, -3}, {4, 5, 6}},
                        "Rectangular matrix with negative and positive numbers"),
                Arguments.of(new int[][]{{-1, -4}, {-2, -5}, {-3, -6}}, new int[][]{{-1, -2, -3}, {-4, -5, -6}},
                        "Rectangular matrix with negative numbers"),
                Arguments.of(new int[][]{{0, 0, 0}}, new int[][]{{0}, {0}, {0}},
                        "Matrix containing only zeros")
        );
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("mergeSortedArraysTest_nullArray_DataProvider")
    public void mergeSortedArraysTest_nullArray_throwsIllegalArgumentException(int[] arr1, int[] arr2, String description) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.mergeSortedArrays(arr1, arr2));

        assertEquals("Array cannot be null.", exception.getMessage());
    }

    static Stream<Arguments> mergeSortedArraysTest_nullArray_DataProvider() {
        return Stream.of(
                Arguments.of(null, new int[]{4, 5, 6}, "The first array is null"),
                Arguments.of(new int[]{1, 2, 3}, null, "The second array is null"),
                Arguments.of(null, null, "Both arrays are null")
        );
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("mergeSortedArraysTest_emptyArray_DataProvider")
    public void mergeSortedArraysTest_emptyArray_throwsIllegalArgumentException(int[] arr1, int[] arr2, String description) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.mergeSortedArrays(arr1, arr2));

        assertEquals("Array cannot be empty.", exception.getMessage());
    }

    static Stream<Arguments> mergeSortedArraysTest_emptyArray_DataProvider() {
        return Stream.of(
                Arguments.of(new int[]{}, new int[]{4, 5, 6}, "The first array is empty"),
                Arguments.of(new int[]{1, 2, 3}, new int[]{}, "The second array is empty"),
                Arguments.of(new int[]{}, new int[]{}, "Both arrays are empty")
        );
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("mergeSortedArrays_notSortedArray_DataProvider")
    public void mergeSortedArraysTest_notSortedArray_throwsIllegalArgumentException(int[] arr1, int[] arr2, String description) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ArrayUtils.mergeSortedArrays(arr1, arr2));

        assertEquals("At least one array is unsorted.", exception.getMessage());
    }

    static Stream<Arguments> mergeSortedArrays_notSortedArray_DataProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2}, new int[]{4, 5, 6}, "arr1 is unsorted"),
                Arguments.of(new int[]{1, 2, 3}, new int[]{6, 4, 5}, "arr2 is unsorted"),
                Arguments.of(new int[]{3, 1, 2}, new int[]{6, 4, 5}, "Both arrays are unsorted")
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("mergeSortedArraysDataProvider")
    public void mergeSortedArraysTest(int[] arr1, int[] arr2, int[] resultArr, String description) {
        assertArrayEquals(resultArr, ArrayUtils.mergeSortedArrays(arr1, arr2));
    }

    static Stream<Arguments> mergeSortedArraysDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{1, 2, 3, 4, 5, 6},
                        "The positive numbers in the first array are less than those in the second; the numbers in the result array are not mixed."),
                Arguments.of(new int[]{4, 5, 6}, new int[]{1, 2, 3}, new int[]{1, 2, 3, 4, 5, 6},
                        "The positive numbers in the first array are bigger than those in the second; the numbers in the result array are not mixed."),
                Arguments.of(new int[]{1, 3, 5}, new int[]{2, 4, 6}, new int[]{1, 2, 3, 4, 5, 6},
                        "The positive numbers in the first array are less than those in the second; the numbers in the result array are mixed."),
                Arguments.of(new int[]{2, 4, 6}, new int[]{1, 3, 5}, new int[]{1, 2, 3, 4, 5, 6},
                        "The positive numbers in the first array are bigger than those in the second; the numbers in the result array are mixed."),
                Arguments.of(new int[]{-3, -2, -1}, new int[]{-6, -5, -4}, new int[]{-6, -5, -4, -3, -2, -1},
                        "The negative numbers in the first array are bigger than those in the second; the numbers in the result array are not mixed."),
                Arguments.of(new int[]{-6, -5, -4}, new int[]{-3, -2, -1}, new int[]{-6, -5, -4, -3, -2, -1},
                        "The negative numbers in the first array are less than those in the second; the numbers in the result array are not mixed."),
                Arguments.of(new int[]{-5, -3, -1}, new int[]{-6, -4, -2}, new int[]{-6, -5, -4, -3, -2, -1},
                        "The negative numbers in the first array are bigger than those in the second; the numbers in the result array are mixed."),
                Arguments.of(new int[]{-6, -4, -2}, new int[]{-5, -3, -1}, new int[]{-6, -5, -4, -3, -2, -1},
                        "The negative numbers in the first array are less than those in the second; the numbers in the result array are mixed."),
                Arguments.of(new int[]{-6, -4, -2}, new int[]{1, 3, 5}, new int[]{-6, -4, -2, 1, 3, 5},
                        "The first array has negative numbers, the second array has positive numbers"),
                Arguments.of(new int[]{1, 3, 5}, new int[]{-6, -4, -2}, new int[]{-6, -4, -2, 1, 3, 5},
                        "The first array has positive numbers, the second array has negative numbers"),
                Arguments.of(new int[]{-6, 3, 5}, new int[]{-4, -2, 1}, new int[]{-6, -4, -2, 1, 3, 5},
                        "Both arrays have positive and negative numbers"),
                Arguments.of(new int[]{1, 3, 3, 5}, new int[]{2, 4, 4, 6}, new int[]{1, 2, 3, 3, 4, 4, 5, 6},
                        "Both arrays have duplicates"),
                Arguments.of(new int[]{3, 3, 3}, new int[]{1, 2, 4, 4, 5, 6}, new int[]{1, 2, 3, 3, 3, 4, 4, 5, 6},
                        "One array consists of duplicates"),
                Arguments.of(new int[]{0, 1, 2}, new int[]{3, 4, 5}, new int[]{0, 1, 2, 3, 4, 5},
                        "There is a zero in one of the arrays"),
                Arguments.of(new int[]{1}, new int[]{2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5},
                        "One array consists of only one number"),
                Arguments.of(new int[]{1}, new int[]{2}, new int[]{1, 2},
                        "Both arrays consist of only one number"),
                Arguments.of(new int[]{0}, new int[]{2, 3, 4, 5}, new int[]{0, 2, 3, 4, 5},
                        "One array consists of only one number and it is zero")
        );
    }
}
