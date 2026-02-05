package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.doubleNumbers_for;

/**
 * Тестирование метода doubleNumbers_for
 * (реализация doubleNumbers через цикл for)
 *
 * @see NumberUtils#doubleNumbers_for(List)
 * @see DoubleNumbersAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Doubling numbers via for-loop")
public class DoubleNumbersForLoopTest extends DoubleNumbersAbstractTest {
    @Override
    protected List<Integer> doubleNumbers(List<Integer> numbers) {
        return doubleNumbers_for(numbers);
    }
}
