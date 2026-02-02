package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.joinStrings_stream;

/**
 * Тестирование метода joinStrings_stream
 * (реализация joinStrings через stream api)
 *
 * @see StringUtils#joinStrings_stream(List, String)
 * @see JoinStringsAbstractTest
 */
public class JoinStringsStreamTest extends JoinStringsAbstractTest {
    @Override
    protected String joinStrings(List<String> strings, String delimiter) {
        return joinStrings_stream(strings, delimiter);
    }
}
