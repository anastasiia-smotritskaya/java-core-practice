package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.sumNumbers_lambda;

/**
 * Тестирование метода sumNumbers_lambda
 * (реализация sumNumbers через лямбда-выражение)
 *
 * @see NumberUtils#sumNumbers_lambda(List)
 * @see SumNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Summation of numbers via stream api")
public class SumNumbersLambdaTest extends SumNumbersAbstractTest {
    @Override
    protected int sumNumbers(List<Integer> numbers) {
        return sumNumbers_lambda(numbers);
    }
}
