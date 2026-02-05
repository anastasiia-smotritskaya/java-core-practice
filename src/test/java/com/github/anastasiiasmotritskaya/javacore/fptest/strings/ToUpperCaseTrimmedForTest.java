package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.toUpperCaseTrimmed_for;

/**
 * Тестирование метода toUpperCase_for
 * (реализация toUpperCase через цикл for)
 *
 * @see StringUtils#toUpperCaseTrimmed_for(List)
 * @see ToUpperCaseTrimmedAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Converting a list of strings to uppercase via for-loop")
public class ToUpperCaseTrimmedForTest extends ToUpperCaseTrimmedAbstractTest {
    @Override
    protected List<String> toUpperCaseTrimmed(List<String> strings) {
        return toUpperCaseTrimmed_for(strings);
    }
}
