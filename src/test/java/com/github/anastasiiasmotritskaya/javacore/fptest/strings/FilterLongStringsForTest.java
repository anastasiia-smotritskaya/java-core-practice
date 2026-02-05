package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.filterLongStrings_for;

/**
 * Тестирование метода filterLongStrings_for
 * (реализация filterLongStrings через цикл for)
 *
 * @see StringUtils#filterLongStrings_for(List, int)
 * @see FilterLongStringsAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Filtering long strings via for-loop")
public class FilterLongStringsForTest extends FilterLongStringsAbstractTest {
    @Override
    protected List<String> filterLongStrings(List<String> strings, int minLength) {
        return filterLongStrings_for(strings, minLength);
    }
}
