package com.github.anastasiiasmotritskaya.javacore.fptest.numbers;

import com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.List;
import java.util.Map;

import static com.github.anastasiiasmotritskaya.javacore.fp.NumberUtils.partitionEvenOdd_for;

/**
 * Тестирование метода partitionEvenOdd_for
 * (реализация partitionEvenOdd через цикл for)
 *
 * @see NumberUtils#partitionEvenOdd_for(List)
 * @see PartitionEvenOddAbstractTest
 */
@Epic("Java Core")
@Feature("Functional programming")
@Story("Dividing numbers into even and odd via for-loop")
public class PartitionEvenOddForTest extends PartitionEvenOddAbstractTest {
    @Override
    protected Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
        return partitionEvenOdd_for(numbers);
    }
}
