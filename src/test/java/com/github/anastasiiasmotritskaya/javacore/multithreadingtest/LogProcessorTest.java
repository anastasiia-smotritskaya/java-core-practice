package com.github.anastasiiasmotritskaya.javacore.multithreadingtest;

import com.github.anastasiiasmotritskaya.javacore.multithreading.LogProcessor;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Java Core")
@Feature("Multithreading")
@Story("Log processor")
public class LogProcessorTest {

    @Test
    @DisplayName("Producer should write and consumer should return the correct queue")
    public void processorLogTest() throws InterruptedException {
        final int threadCount = 5;
        ConcurrentLinkedQueue<String> expected = new ConcurrentLinkedQueue<>();
        expected.add("Log #0");
        expected.add("Log #1");
        expected.add("Log #2");
        expected.add("Log #3");

        LogProcessor lp = new LogProcessor();
        lp.produceLogs(threadCount);
        lp.consumeLogs();

        assertEquals(4, lp.getConsumerQueue().size());
        assertEquals(Set.copyOf(expected), Set.copyOf(lp.getConsumerQueue()));
    }
}
