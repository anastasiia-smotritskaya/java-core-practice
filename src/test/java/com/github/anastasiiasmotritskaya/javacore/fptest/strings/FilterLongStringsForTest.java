package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import com.github.anastasiiasmotritskaya.javacore.fptest.numbers.FilterEvenNumbersAbstractTest;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.filterLongStrings_for;

/**
 * Тестирование метода filterLongStrings_for
 * (реализация filterLongStrings через цикл for)
 *
 * @see StringUtils#filterLongStrings_for(List, int)
 * @see FilterLongStringsAbstractTest
 */
public class FilterLongStringsForTest extends FilterLongStringsAbstractTest {
    @Override
    protected List<String> filterLongStrings(List<String> strings, int minLength) {
        return filterLongStrings_for(strings, minLength);
    }
}
