package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;

import java.util.List;
import java.util.Optional;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.findMax_stream;

/**
 * Тестирование метода findMax_stream
 * (реализация findMax через stream api)
 *
 * @see NumberUtils#findMax_stream(List)
 * @see FindMaxAbstractTest
 */
public class FindMaxStreamTest extends FindMaxAbstractTest {
    @Override
    protected Optional<Integer> findMax(List<Integer> numbers) {
        return findMax_stream(numbers);
    }
}
