package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_mr
 * (реализация filterEvenNumbers через method references)
 *
 * @see NumberUtils#filterEvenNumbers_mr(List)
 * @see FilterEvenNumbersAbstractTest
 */
public class FilterEvenNumbersMRTest extends FilterEvenNumbersAbstractTest {
    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_mr(numbers);
    }
}
