package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.containsSubstring_stream;

/**
 * Тестирование метода containsSubstring_stream
 * (реализация containsSubstring через stream api)
 *
 * @see StringUtils#containsSubstring_stream(List, String)
 * @see ContainsSubstringAbstractTest
 */
public class ContainsSubstringStreamTest extends ContainsSubstringAbstractTest {
    @Override
    protected boolean containsSubstring(List<String> strings, String substring) {
        return containsSubstring_stream(strings, substring);
    }
}
