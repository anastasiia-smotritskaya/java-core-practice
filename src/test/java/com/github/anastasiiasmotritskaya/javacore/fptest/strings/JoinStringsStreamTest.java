package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.joinStrings_stream;

/**
 * Тестирование метода joinStrings_stream
 * (реализация joinStrings через stream api)
 *
 * @see StringUtils#joinStrings_stream(List, String)
 * @see JoinStringsAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Joining strings via stream api")
public class JoinStringsStreamTest extends JoinStringsAbstractTest {
    @Override
    protected String joinStrings(List<String> strings, String delimiter) {
        return joinStrings_stream(strings, delimiter);
    }
}
