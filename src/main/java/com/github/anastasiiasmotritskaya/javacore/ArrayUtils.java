package com.github.anastasiiasmotritskaya.javacore;

import java.util.Arrays;

/**
 * Работа с одномерными и двумерными массивами
 * Методы: поиск максимума и минимума, пузырьковая сортировка, поиска элемента в двумерном массиве, транспонирование матрицы
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class ArrayUtils {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private ArrayUtils() {
    }

    /**
     * Находит максимальный и минимальный элемент в массиве
     *
     * @param array массив целых чисел
     * @return массив из двух элементов [min, max]
     * {@code int[] minMaxArray = findMinMax(new int[]{5, 2, 8, 1, 9})} || [1, 9]
     * {@code int[] minMaxArray = findMinMax(new int[]{-10, 0, 10})} || [-10, 10]
     * @throws IllegalArgumentException если массив null или пустой
     */
    public static int[] findMinMax(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The array must not be null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("The array must not be empty.");
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i : array) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        return new int[]{min, max};
    }

    /**
     * Сортирует массив методом пузырька
     *
     * @param array массив для сортировки (исходный массив модифицируется)
     *              {@code int[] bubbleSort = bubbleSort(new int[]{5, 2, 8, 1, 9})} || [1, 2, 5, 8, 9]
     * @return отсортированный массив (тот же массив)
     * @throws IllegalArgumentException если массив null или пустой
     */
    public static int[] bubbleSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The array must not be null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("The array must not be empty.");
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * Ищет элемент в двумерном массиве
     *
     * @param matrix двумерный массив целых чисел
     * @param target элемент для поиска
     * @return массив с координатами [row, col] или [-1, -1] если не найден
     * {@code int[] targetCoordinate = findInMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 4)}); || [1, 0]
     * @throws IllegalArgumentException если matrix null или пустой
     */
    public static int[] findInMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty (0 rows).");
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) continue;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Транспонирует матрицу (меняет строки и столбцы местами)
     *
     * @param matrix исходная матрица
     * @return транспонированная матрица
     * {@code int[][] transposedMatrix = transposeMatrix(new int[][]{{1, 2, 3}, {4, 5, 6});}} || {{1, 4}, {2, 5}, {3, 6}}
     * @throws IllegalArgumentException если матрица не прямоугольная или null
     */
    public static int[][] transposeMatrix(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty (0 rows).");
        }

        for (int[] row : matrix) {
            if (row == null || row.length == 0) {
                throw new IllegalArgumentException("The matrix cannot have empty rows.");
            }
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            if (matrix[i].length != matrix[i + 1].length) {
                throw new IllegalArgumentException("The matrix must be rectangular.");
            }
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transposedMatrix = new int[cols][rows];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int number = matrix[i][j];
                transposedMatrix[j][i] = number;
            }
        }

        return transposedMatrix;
    }

    /**
     * Объединяет два отсортированных массива в один отсортированный
     *
     * @param arr1 первый отсортированный массив
     * @param arr2 второй отсортированный массив
     * @return новый отсортированный массив, содержащий все элементы
     * {@code int[] resultArr = mergeSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6})}; // {1, 2, 3, 4, 5, 6}
     * @throws IllegalArgumentException если массивы не отсортированы, пустые или null
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        boolean areSorted = true;

        for (int i = 0; i < arr1.length - 1; i++) {
            if (arr1[i] > arr1[i + 1]) {
                areSorted = false;
            }
        }

        for (int i = 0; i < arr2.length - 1; i++) {
            if (arr2[i] > arr2[i + 1]) {
                areSorted = false;
            }
        }

        if (!areSorted) {
            throw new IllegalArgumentException("At least one array is unsorted.");
        }

        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];

        return result;
    }
}
