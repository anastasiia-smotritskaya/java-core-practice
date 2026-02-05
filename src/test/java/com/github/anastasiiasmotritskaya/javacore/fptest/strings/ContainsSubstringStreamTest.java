package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.containsSubstring_stream;

/**
 * Тестирование метода containsSubstring_stream
 * (реализация containsSubstring через stream api)
 *
 * @see StringUtils#containsSubstring_stream(List, String)
 * @see ContainsSubstringAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Finding a substring in a list of strings via stream-api")
public class ContainsSubstringStreamTest extends ContainsSubstringAbstractTest {
    @Override
    protected boolean containsSubstring(List<String> strings, String substring) {
        return containsSubstring_stream(strings, substring);
    }
}
