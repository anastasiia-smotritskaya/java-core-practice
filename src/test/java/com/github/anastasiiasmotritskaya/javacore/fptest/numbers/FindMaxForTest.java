package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

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
public class FindMaxForTest extends FindMaxAbstractTest {
    @Override
    protected Optional<Integer> findMax(List<Integer> numbers) {
        return findMax_for(numbers);
    }
}
