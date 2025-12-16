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
}
