package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.sortByLength_stream;

/**
 * Тестирование метода sortByLength_stream
 * (реализация sortByLength через stream api)
 *
 * @see StringUtils#sortByLength_stream(List)
 * @see SortByLengthAbstractTest
 */
public class SortByLengthStreamTest extends SortByLengthAbstractTest {
    @Override
    protected List<String> sortByLength(List<String> strings) {
        return sortByLength_stream(strings);
    }
}
