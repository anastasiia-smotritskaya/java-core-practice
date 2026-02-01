package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.filterLongStrings_mr;

/**
 * Тестирование метода filterLongStrings_mr
 * (реализация filterLongStrings через method reference и доп. лямбда-выражение)
 *
 * @see StringUtils#filterLongStrings_mr(List, int)
 * @see FilterLongStringsAbstractTest
 */
public class FilterLongStringsMRTest extends FilterLongStringsAbstractTest {
    @Override
    protected List<String> filterLongStrings(List<String> strings, int minLength) {
        return filterLongStrings_mr(strings, minLength);
    }
}
