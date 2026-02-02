package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.joinStrings_for;

/**
 * Тестирование метода joinStrings_for
 * (реализация joinStrings через цикл for)
 *
 * @see StringUtils#joinStrings_for(List, String)
 * @see JoinStringsAbstractTest
 */
public class JoinStringsForTest extends JoinStringsAbstractTest {
    @Override
    protected String joinStrings(List<String> strings, String delimiter) {
        return joinStrings_for(strings, delimiter);
    }
}
