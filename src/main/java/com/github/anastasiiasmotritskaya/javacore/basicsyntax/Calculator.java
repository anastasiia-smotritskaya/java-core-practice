package com.github.anastasiiasmotritskaya.javacore.basicsyntax;

/**
 * Базовый калькулятор
 * Методы: сложение, вычитание, умножение, деление, проверка на четность
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Calculator {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private Calculator() {
    }

    /**
     * Складывает два числа типа double
     *
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return результат сложения типа double
     * {@code double result = add(15, 5);} // 20.0
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Вычитает из одного числа типа double другое число типа double
     *
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return результат вычитания типа double
     * {@code double result = subtract(15, 5);} // 10.0
     */
    public static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Умножает одно число типа double на другое число типа double
     *
     * @param a множитель
     * @param b множитель
     * @return результат умножения типа double
     * {@code double result = subtract(15, 5);} // 75.0
     */
    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Делит одно число типа double на другое число типа double
     *
     * @param a делимое
     * @param b делитель
     * @return результат деления типа double
     * {@code double result = divide(15, 5);} // 3.0
     * @throws IllegalArgumentException если b равен 0
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        } else {
            return a / b;
        }
    }

    /**
     * Проверяет на четность число типа int
     *
     * @param number число, которое необходимо проверить на четность
     * @return true если число четное, false если число нечетное
     * {@code boolean result = isEven(2);} // true
     * {@code boolean result = isEven(3);} // false
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
