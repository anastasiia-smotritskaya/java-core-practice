package com.github.anastasiiasmotritskaya.javacore.basicsyntaxtest;

import com.github.anastasiiasmotritskaya.javacore.basicsyntax.ConditionalOperators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ConditionalOperatorsTest {

    @ParameterizedTest(name = "Score {0} should be grade {1}")
    @CsvSource(value = {
            "0, F", "1, F", "59, F",
            "60, D", "65, D", "69, D",
            "70, C", "75, C", "79, C",
            "80, B", "85, B", "89, B",
            "90, A", "95, A", "99, A", "100, A"
    })
    public void convertToGradeTest(int score, char mark) {
        assertAll(
                () -> Assertions.assertEquals(mark, ConditionalOperators.convertToGrade_if(score)),
                () -> assertEquals(mark, ConditionalOperators.convertToGrade_switch(score)),
                () -> assertEquals(mark, ConditionalOperators.convertToGrade_switch_exp(score))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -50, 101, 150})
    public void convertToGradeIllegalArgumentTest(int score) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> ConditionalOperators.convertToGrade_if(score));
                    assertEquals("The rating is incorrect! Rating range: 0 to 100 points", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> ConditionalOperators.convertToGrade_switch(score));
                    assertEquals("The rating is incorrect! Rating range: 0 to 100 points", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                            () -> ConditionalOperators.convertToGrade_switch_exp(score));
                    assertEquals("The rating is incorrect! Rating range: 0 to 100 points", exception.getMessage());
                }
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "12, Winter", "1, Winter", "2, Winter",
            "3, Spring", "4, Spring", "5, Spring",
            "6, Summer", "7, Summer", "8, Summer",
            "9, Autumn", "10, Autumn", "11, Autumn"})
    public void getSeasonTest(int month, String season) {
        assertAll(
                () -> assertEquals(season, ConditionalOperators.getSeason_if(month)),
                () -> assertEquals(season, ConditionalOperators.getSeason_switch(month)),
                () -> assertEquals(season, ConditionalOperators.getSeason_switch_exp(month))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 13, 22})
    public void getSeasonIllegalArgumentTest(int illegalMonth) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.getSeason_if(illegalMonth));
                    assertEquals("The month number is entered incorrectly! The month number must be between 1 and 12.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.getSeason_switch(illegalMonth));
                    assertEquals("The month number is entered incorrectly! The month number must be between 1 and 12.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.getSeason_switch_exp(illegalMonth));
                    assertEquals("The month number is entered incorrectly! The month number must be between 1 and 12.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -100.00})
    public void calculateDiscountIllegalArgumentTest(double price) {
        assertAll(
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.calculateDiscount_if(price));
                    assertEquals("The price of a product cannot be less than zero.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.calculateDiscount_switch(price));
                    assertEquals("The price of a product cannot be less than zero.", exception.getMessage());
                },
                () -> {
                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ConditionalOperators.calculateDiscount_switch_exp(price));
                    assertEquals("The price of a product cannot be less than zero.", exception.getMessage());
                }
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0.00, 0.00", "500.00, 500.00", "999.99, 999.99",
            "1000.00, 950.00", "1000.01, 950.01", "2000.00, 1900.00", "4999.99, 4749.99",
            "5000.00, 4500.00", "5000.01, 4500.01", "6000.00, 5400.00", "100_000.00, 90_000.00"
    })
    public void calculateDiscountTest(double price, double discountedPrice) {
        assertAll(
                () -> assertEquals(discountedPrice, ConditionalOperators.calculateDiscount_if(price)),
                () -> assertEquals(discountedPrice, ConditionalOperators.calculateDiscount_switch(price)),
                () -> assertEquals(discountedPrice, ConditionalOperators.calculateDiscount_switch_exp(price))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "-0.01, true",
            "-100.00, false",
            "0.00, true",
            "0.01, false",
            "0.99, true"
    })
    public void calculateDiscountWithCardIllegalArgumentTest(double price, boolean hasCard) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConditionalOperators.calculateDiscountWithCard(price, hasCard));

        assertEquals("The price of a product cannot be less than 1.00.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "1.00, 1.00, false", "500.00, 500.00, false", "999.99, 999.99, false",
            "1000.00, 950.00, false", "1000.01, 950.01, false", "2000.00, 1900.00, false", "4999.99, 4749.99, false",
            "5000.00, 4500.00, false", "5000.01, 4500.01, false", "6000.00, 5400.00, false", "100_000.00, 90_000.00, false"
    })
    public void calculateDiscountNoCardTest(double price, double discountedPrice, boolean hasCard) {
        assertEquals(discountedPrice, ConditionalOperators.calculateDiscountWithCard(price, hasCard));
    }

    @ParameterizedTest
    @CsvSource({
            "1.01, 1.00, true", "1.02, 1.00, true",
            "1.00, 1.00, true", "500.00, 490.00, true", "999.99, 979.99, true",
            "1000.00, 931.00, true", "1000.01, 931.01, true", "2000.00, 1862.00, true", "4999.99, 4654.99, true",
            "5000.00, 4410.00, true", "5000.01, 4410.01, true", "6000.00, 5292.00, true", "100_000.00, 88_200.00, true"
    })
    public void calculateDiscountWithCardTest(double price, double discountedPrice, boolean hasCard) {
        assertEquals(discountedPrice, ConditionalOperators.calculateDiscountWithCard(price, hasCard));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 4, 5", "3, 0, 5", "3, 4, 0",
            "0, 0, 5", "3, 0, 0", " 0, 4, 0",
            "0, 0, 0",
            "-3, 4, 5", "3, -4, 5", "3, 4, -5",
            "-3, -4, 5", "3, -4, -5", " -3, 4, -5",
            "-3, -4, -5", "-15, -3, -4"
    })
    public void triangleTypeZeroTest(int a, int b, int c) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConditionalOperators.triangleType(a, b, c));

        assertEquals("The length of a side of a triangle must be greater than zero.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "15, 3, 4", "3, 15, 4", "3, 4, 15"
    })
    public void triangleTypeGreaterThanThirdTest(int a, int b, int c) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ConditionalOperators.triangleType(a, b, c));

        assertEquals("A triangle is possible if the sum of any two sides is greater than the third.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 5, Equilateral",
            "6, 6, 5, Isosceles",
            "3, 4, 5, Scalene: all sides are different"
    })
    public void triangleTypeTest(int a, int b, int c, String triangleType) {
        assertEquals(triangleType, ConditionalOperators.triangleType(a, b, c));
    }
}
