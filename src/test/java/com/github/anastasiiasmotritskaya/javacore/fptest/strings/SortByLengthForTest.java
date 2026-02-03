package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.sortByLength_for;

/**
 * Тестирование метода sortByLength_for
 * (реализация sortByLength через цикл for)
 *
 * @see StringUtils#sortByLength_for(List)
 * @see SortByLengthAbstractTest
 */
public class SortByLengthForTest extends SortByLengthAbstractTest {
    @Override
    protected List<String> sortByLength(List<String> strings) {
        return sortByLength_for(strings);
    }
}
