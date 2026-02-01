package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.filterLongStrings_lambda;

/**
 * Тестирование метода filterLongStrings_lambda
 * (реализация filterLongStrings через лямбда-выражение)
 *
 * @see StringUtils#filterLongStrings_lambda(List, int)
 * @see FilterLongStringsAbstractTest
 */
public class FilterLongStringsLambdaTest extends FilterLongStringsAbstractTest {
    @Override
    protected List<String> filterLongStrings(List<String> strings, int minLength) {
        return filterLongStrings_lambda(strings, minLength);
    }
}
