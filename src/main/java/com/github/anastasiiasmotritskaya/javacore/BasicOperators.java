package com.github.anastasiiasmotritskaya.javacore;

public class BasicOperators {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private BasicOperators() {
    }

    /**
     * Вычисляет частное и остаток от деления
     *
     * @param a делимое
     * @param b делитель
     * @return массив [частное, остаток]
     * {@code int[] result = divideWithRemainder(15, 5);} // [3, 0]
     * @throws IllegalArgumentException если b равен 0
     */
    public static int[] divideWithRemainder(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }
        int[] result = new int[2];
        result[0] = a / b;
        result[1] = a % b;
        return result;
    }
}
