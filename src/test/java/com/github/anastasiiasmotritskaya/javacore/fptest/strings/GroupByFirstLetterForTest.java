package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

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
@Epic("Java Core")
@Feature("Functional programming")
@Story("Grouping by first letter via for-loop")
public class GroupByFirstLetterForTest extends GroupByFirstLetterAbstractTest {
    @Override
    protected Map<Character, List<String>> groupByFirstLetter(List<String> strings) {
        return groupByFirstLetter_for(strings);
    }
}
