package com.github.anastasiiasmotritskaya.javacore.fptest.strings;

import com.github.anastasiiasmotritskaya.javacore.fp.StringUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;
import java.util.Map;

import static com.github.anastasiiasmotritskaya.javacore.fp.StringUtils.countWordFrequency_stream;

/**
 * Тестирование метода countWordFrequency_stream
 * (реализация countWordFrequency через stream api)
 *
 * @see StringUtils#countWordFrequency_stream(List)
 * @see CountWordFrequencyAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Counting word frequency in a list of words via stream api")
public class CountWordFrequencyStreamTest extends CountWordFrequencyAbstractTest {
    @Override
    protected Map<String, Long> countWordFrequency(List<String> words) {
        return countWordFrequency_stream(words);
    }
}
