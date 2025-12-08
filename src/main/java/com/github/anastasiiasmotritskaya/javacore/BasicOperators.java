package com.github.anastasiiasmotritskaya.javacore;

/**
 * Работа с примитивами и операторами
 * Методы: вычисление частного и остатка, конвертер температур, проверка переполнения
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
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

    /**
     * Конвертирует температуру между шкалами
     *
     * @param value значение температуры
     * @param from  исходная шкала ("C", "F", "K")
     * @param to    целевая шкала ("C", "F", "K")
     * @return конвертированная температура
     * {@code double result = convertTemperature(-273.15, "C", "K");} // 0.0
     * @throws IllegalArgumentException если {@code from} или {@code to}
     *                                  не равны "C", "F" или "K"
     * @throws IllegalArgumentException если температура в Кельвинах ниже
     *                                  абсолютного нуля (0K)
     * @throws IllegalArgumentException если температура в градусах Цельсия
     *                                  ниже -273.15°C при конвертации в Кельвины
     * @throws IllegalArgumentException если температура в градусах Фаренгейта
     *                                  ниже -459.67°F при конвертации в Кельвины
     */
    public static double convertTemperature(double value, String from, String to) {

        double result = 0.0;

        if (from == null || to == null) throw new IllegalArgumentException("Scale cannot be null");

        if (!isValidScale(from) || !isValidScale(to))
            throw new IllegalArgumentException("This is not a scale! You must select Celsius (C), Fahrenheit (F), or Kelvin (K).");

        if (from.equalsIgnoreCase("C")) {
            if (to.equalsIgnoreCase(from)) result = value;
            else if (to.equalsIgnoreCase("K") && value < -273.15)
                throw new IllegalArgumentException("Kelvins are never negative. Check the data.");
            else if (to.equalsIgnoreCase("K") && value >= -273.15) result = value + 273.15;
            else if (to.equalsIgnoreCase("F")) result = (value * 9.0 / 5.0) + 32.0;

        } else if (from.equalsIgnoreCase("F")) {
            if (to.equalsIgnoreCase(from)) result = value;
            else if (to.equalsIgnoreCase("C")) result = (value - 32.0) * 5.0 / 9.0;
            else if (to.equalsIgnoreCase("K") && value < -459.67)
                throw new IllegalArgumentException("Kelvins are never negative. Check the data.");
            else if (to.equalsIgnoreCase("K") && value >= -459.67) result = (value - 32.0) * 5.0 / 9.0 + 273.15;

        } else if (from.equalsIgnoreCase("K")) {
            if (value < 0.0) throw new IllegalArgumentException("Kelvins are never negative. Check the data.");
            else if (to.equalsIgnoreCase(from)) result = value;
            else if (to.equalsIgnoreCase("C")) result = value - 273.15;
            else if (to.equalsIgnoreCase("F")) result = (value - 273.15) * 9.0 / 5.0 + 32.0;
        }
        return result;
    }

    private static boolean isValidScale(String scale) {
        return scale != null
                && (scale.equalsIgnoreCase("C")
                || scale.equalsIgnoreCase("K")
                || scale.equalsIgnoreCase("F"));
    }

    /**
     * Проверяет, вызовет ли арифметическая операция переполнение типа int
     *
     * @param a первое число
     * @param b второе число
     * @param operator арифметический оператор ("+", "-", "*", "/")
     * @return true если операция вызовет переполнение
     * @throws IllegalArgumentException если:
     *         <ul>
     *           <li>operator не "+", "-", "*", или "/"</li>
     *           <li>происходит деление на ноль (b == 0 при operator == "/")</li>
     *         </ul>
     * {@code boolean result = willOverflow(Integer.MAX_VALUE, 1, "+");} // true
     */
    public static boolean willOverflow(int a, int b, String operator) {

        switch (operator) {
            case "+":
                try {
                    Math.addExact(a, b);
                    return false;
                } catch (ArithmeticException e) {
                    return true;
                }

            case "-":
                try {
                    Math.subtractExact(a, b);
                    return false;
                } catch (ArithmeticException e) {
                    return true;
                }

            case "*":
                try {
                    Math.multiplyExact(a, b);
                    return false;
                } catch (ArithmeticException e) {
                    return true;
                }

            case "/":
                if (b == 0) throw new IllegalArgumentException("You can't divide by zero");
                try {
                    Math.divideExact(a, b);
                    return false;
                } catch (ArithmeticException e) {
                    return true;
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
