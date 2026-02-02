package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.toUpperCaseTrimmed_stream;

/**
 * Тестирование метода toUpperCase_stream
 * (реализация toUpperCase через stream api)
 *
 * @see StringUtils#toUpperCaseTrimmed_stream(List)
 * @see ToUpperCaseTrimmedAbstractTest
 */
public class ToUpperCaseTrimmedStreamTest extends ToUpperCaseTrimmedAbstractTest {
    @Override
    protected List<String> toUpperCaseTrimmed(List<String> strings) {
        return toUpperCaseTrimmed_stream(strings);
    }
}
