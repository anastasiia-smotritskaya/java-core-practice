package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.doubleNumbers_lambda;

/**
 * Тестирование метода doubleNumbers_lambda
 * (реализация doubleNumbers через лямбда-выражение)
 *
 * @see NumberUtils#doubleNumbers_lambda(List)
 * @see DoubleNumbersAbstractTest
 */
public class DoubleNumbersLambdaTest extends DoubleNumbersAbstractTest {
    @Override
    protected List<Integer> doubleNumbers(List<Integer> numbers) {
        return doubleNumbers_lambda(numbers);
    }
}
