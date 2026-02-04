package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;
import java.util.Map;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.groupByFirstLetter_stream;

/**
 * Тестирование метода groupByFirstLetter_stream
 * (реализация groupByFirstLetter через stream api)
 *
 * @see StringUtils#groupByFirstLetter_stream(List)
 * @see GroupByFirstLetterAbstractTest
 */
public class GroupByFirstLetterStreamTest extends GroupByFirstLetterAbstractTest {
    @Override
    protected Map<Character, List<String>> groupByFirstLetter(List<String> strings) {
        return groupByFirstLetter_stream(strings);
    }
}
