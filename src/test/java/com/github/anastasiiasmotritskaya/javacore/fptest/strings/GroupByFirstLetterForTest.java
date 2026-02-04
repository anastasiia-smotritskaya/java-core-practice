package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;

import java.util.List;
import java.util.Map;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.groupByFirstLetter_for;

/**
 * Тестирование метода groupByFirstLetter_for
 * (реализация groupByFirstLetter через цикл for)
 *
 * @see StringUtils#groupByFirstLetter_for(List)
 * @see GroupByFirstLetterAbstractTest
 */
public class GroupByFirstLetterForTest extends GroupByFirstLetterAbstractTest {
    @Override
    protected Map<Character, List<String>> groupByFirstLetter(List<String> strings) {
        return groupByFirstLetter_for(strings);
    }
}
