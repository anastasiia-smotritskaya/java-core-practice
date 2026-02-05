package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.doubleNumbers_mr;

/**
 * Тестирование метода doubleNumbers_mr
 * (реализация doubleNumbers через method references)
 *
 * @see NumberUtils#doubleNumbers_mr(List)
 * @see DoubleNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Doubling numbers via method reference")
public class DoubleNumbersMRTest extends DoubleNumbersAbstractTest {
    @Override
    protected List<Integer> doubleNumbers(List<Integer> numbers) {
        return doubleNumbers_mr(numbers);
    }
}
