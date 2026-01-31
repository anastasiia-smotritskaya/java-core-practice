package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

/**
 * Тестирование метода filterEvenNumbers_for
 * (реализация filterEvenNumbers через цикл for)
 *
 * @see NumberUtils#filterEvenNumbers_for(List)
 * @see FilterEvenNumbersAbstractTest
 */
public class FilterEvenNumbersForLoopTest extends FilterEvenNumbersAbstractTest {

    @Override
    protected List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return NumberUtils.filterEvenNumbers_for(numbers);
    }
}
