package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.toUpperCaseTrimmed_stream;

/**
 * Тестирование метода toUpperCase_stream
 * (реализация toUpperCase через stream api)
 *
 * @see StringUtils#toUpperCaseTrimmed_stream(List)
 * @see ToUpperCaseTrimmedAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Converting a list of strings to uppercase via stream api")
public class ToUpperCaseTrimmedStreamTest extends ToUpperCaseTrimmedAbstractTest {
    @Override
    protected List<String> toUpperCaseTrimmed(List<String> strings) {
        return toUpperCaseTrimmed_stream(strings);
    }
}
