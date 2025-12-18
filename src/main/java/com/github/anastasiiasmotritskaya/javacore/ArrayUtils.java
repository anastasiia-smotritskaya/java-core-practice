package com.github.anastasiiasmotritskaya.javacore;

/**
 * Работа с одномерными и двумерными массивами
 * Методы: поиск максимума и минимума
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
     * @param array массив для сортировки (исходный массив модифицируется)
     * {@code int[] bubbleSort = bubbleSort(new int[]{5, 2, 8, 1, 9})} || [1, 2, 5, 8, 9]
     * @throws IllegalArgumentException если массив null или пустой
     * @return отсортированный массив (тот же массив)
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
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }
}
