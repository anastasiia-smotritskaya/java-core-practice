package com.github.anastasiiasmotritskaya.javacore;

import org.junit.jupiter.api.Test;
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
                () -> assertEquals(mark, ConditionalOperators.convertToGrade_if(score)),
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
}
