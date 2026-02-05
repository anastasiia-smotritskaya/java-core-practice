package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.sortByLength_for;

/**
 * Тестирование метода sortByLength_for
 * (реализация sortByLength через цикл for)
 *
 * @see StringUtils#sortByLength_for(List)
 * @see SortByLengthAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Sorting by length via for-loop")
public class SortByLengthForTest extends SortByLengthAbstractTest {
    @Override
    protected List<String> sortByLength(List<String> strings) {
        return sortByLength_for(strings);
    }
}
