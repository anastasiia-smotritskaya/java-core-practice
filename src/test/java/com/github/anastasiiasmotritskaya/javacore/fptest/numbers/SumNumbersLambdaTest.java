package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.sumNumbers_lambda;

/**
 * Тестирование метода sumNumbers_lambda
 * (реализация sumNumbers через лямбда-выражение)
 *
 * @see NumberUtils#sumNumbers_lambda(List)
 * @see SumNumbersAbstractTest
 */
public class SumNumbersLambdaTest extends SumNumbersAbstractTest {
    @Override
    protected int sumNumbers(List<Integer> numbers) {
        return sumNumbers_lambda(numbers);
    }
}
