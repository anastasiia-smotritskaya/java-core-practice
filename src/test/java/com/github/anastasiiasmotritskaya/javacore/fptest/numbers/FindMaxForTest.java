package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;
import java.util.Optional;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.findMax_for;

/**
 * Тестирование метода findMax_for
 * (реализация findMax через цикл for)
 *
 * @see NumberUtils#findMax_for(List)
 * @see FindMaxAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Finding max number via for-loop")
public class FindMaxForTest extends FindMaxAbstractTest {
    @Override
    protected Optional<Integer> findMax(List<Integer> numbers) {
        return findMax_for(numbers);
    }
}
