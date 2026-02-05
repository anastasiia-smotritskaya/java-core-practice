package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.sumNumbers_for;

/**
 * Тестирование метода sumNumbers_for
 * (реализация sumNumbers через цикл for)
 *
 * @see NumberUtils#sumNumbers_for(List)
 * @see SumNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Summation of numbers via for-loop")
public class SumNumbersForLoopTest extends SumNumbersAbstractTest {
    @Override
    protected int sumNumbers(List<Integer> numbers) {
        return sumNumbers_for(numbers);
    }
}
