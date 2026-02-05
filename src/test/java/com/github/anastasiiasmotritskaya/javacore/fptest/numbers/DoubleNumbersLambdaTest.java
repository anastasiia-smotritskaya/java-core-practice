package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.doubleNumbers_lambda;

/**
 * Тестирование метода doubleNumbers_lambda
 * (реализация doubleNumbers через лямбда-выражение)
 *
 * @see NumberUtils#doubleNumbers_lambda(List)
 * @see DoubleNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Doubling numbers via stream api")
public class DoubleNumbersLambdaTest extends DoubleNumbersAbstractTest {
    @Override
    protected List<Integer> doubleNumbers(List<Integer> numbers) {
        return doubleNumbers_lambda(numbers);
    }
}
