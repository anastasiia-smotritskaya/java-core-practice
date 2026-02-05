package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_mr
 * (реализация filterEvenNumbers через method references)
 *
 * @see NumberUtils#filterEvenNumbers_mr(List)
 * @see FilterEvenNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Filtering numbers via method reference")
public class FilterEvenNumbersMRTest extends FilterEvenNumbersAbstractTest {
    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_mr(numbers);
    }
}
