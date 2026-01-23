package com.github.anastasiiasmotritskaya.javacore.util;

/**
 * Валидация параметров методов класса Library
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public final class LibraryValidator {
    /**
     * Приватный конструктор для предотвращения создания экземпляров.
     */
    private LibraryValidator() {
    }

    /**
     * Проверка того, что начальный год меньше или равен конечному году
     *
     * @throws IllegalArgumentException если начальный год больше конченого года
     */
    public static void validateYearRange(int fromYear, int toYear) {
        if (fromYear > toYear) {
            throw new IllegalArgumentException(String.format("The toYear is less than fromYear. " +
                    "You entered: fromYear: %d, toYear: %d. Please enter the correct toYear of publication.", fromYear, toYear));
        }
    }
}
