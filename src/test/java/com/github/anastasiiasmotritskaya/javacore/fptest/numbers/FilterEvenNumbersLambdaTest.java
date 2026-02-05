package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_lambda
 * (реализация filterEvenNumbers через лямбда-выражение)
 *
 * @see NumberUtils#filterEvenNumbers_lambda(List)
 * @see FilterEvenNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Filtering numbers via stream api")
public class FilterEvenNumbersLambdaTest extends FilterEvenNumbersAbstractTest {
    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_lambda(numbers);
    }
}
