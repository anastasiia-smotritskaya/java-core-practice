package com.github.anastasiiasmotritskaya.javacore.fptest.filtertest;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_lambda
 * (реализация filterEvenNumbers через лямбда-выражение)
 *
 * @see NumberUtils#filterEvenNumbers_lambda(List)
 * @see FilterEvenNumbersAbstractTest
 */
public class FilterEvenNumbersLambdaTest extends FilterEvenNumbersAbstractTest {
    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_lambda(numbers);
    }
}
