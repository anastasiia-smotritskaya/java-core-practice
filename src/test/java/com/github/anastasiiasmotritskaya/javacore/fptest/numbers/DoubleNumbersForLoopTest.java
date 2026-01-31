package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.doubleNumbers_for;

/**
 * Тестирование метода doubleNumbers_for
 * (реализация doubleNumbers через цикл for)
 *
 * @see NumberUtils#doubleNumbers_for(List)
 * @see DoubleNumbersAbstractTest
 */
public class DoubleNumbersForLoopTest extends DoubleNumbersAbstractTest {
    @Override
    protected List<Integer> doubleNumbers(List<Integer> numbers) {
        return doubleNumbers_for(numbers);
    }
}
