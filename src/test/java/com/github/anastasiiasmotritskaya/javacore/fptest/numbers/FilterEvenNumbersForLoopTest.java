package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_for
 * (реализация filterEvenNumbers через цикл for)
 *
 * @see NumberUtils#filterEvenNumbers_for(List)
 * @see FilterEvenNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Filtering numbers via for-loop")
public class FilterEvenNumbersForLoopTest extends FilterEvenNumbersAbstractTest {
    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_for(numbers);
    }
}
