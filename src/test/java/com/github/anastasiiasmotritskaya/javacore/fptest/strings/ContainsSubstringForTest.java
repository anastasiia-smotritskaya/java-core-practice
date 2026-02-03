package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.containsSubstring_for;

/**
 * Тестирование метода containsSubstring_for
 * (реализация containsSubstring через цикл for)
 *
 * @see StringUtils#containsSubstring_for(List, String)
 * @see ContainsSubstringAbstractTest
 */
public class ContainsSubstringForTest extends ContainsSubstringAbstractTest {
    @Override
    protected boolean containsSubstring(List<String> strings, String substring) {
        return containsSubstring_for(strings, substring);
    }
}
