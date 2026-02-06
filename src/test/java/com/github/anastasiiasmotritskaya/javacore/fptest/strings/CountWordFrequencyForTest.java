package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;
import java.util.Map;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.countWordFrequency_for;

/**
 * Тестирование метода countWordFrequency_for
 * (реализация countWordFrequency через цикл for)
 *
 * @see StringUtils#countWordFrequency_for(List)
 * @see CountWordFrequencyAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Counting word frequency in a list of words via for-loop")
public class CountWordFrequencyForTest extends CountWordFrequencyAbstractTest {
    @Override
    protected Map<String, Long> countWordFrequency(List<String> words) {
        return countWordFrequency_for(words);
    }
}
