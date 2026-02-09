package com.github.anastasiiasmotritskaya.javacore.multithreadingtest;

import com.github.anastasiiasmotritskaya.javacore.multithreading.Counter;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Java Core")
@Feature("Multithreading")
@Story("Counter testing")
public class CounterTest {

    @Test
    @DisplayName("Unsafe increment method should return a counter value less than expected")
    public void counterTest() throws InterruptedException {
        boolean raceConditionDetected = false;

        for (int attempt = 0; attempt < 10; attempt++) {
            Counter counter = new Counter();
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        counter.increment();
                    }
                });
                threads.add(t);
            }

            for (Thread t : threads) {
                t.start();
            }

            for (Thread t : threads) {
                t.join();
            }

            if (counter.getCounter() < 10_000) {
                raceConditionDetected = true;
                break;
            }
        }
        assertTrue(raceConditionDetected, "Race condition was not detected in 10 attempts.");
    }

    @Test
    @DisplayName("Safe increment method should return a counter value equal to the expected one")
    public void counterSyncTest() throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementSync();
                }
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        assertEquals(10_000, counter.getCounter());
    }
}
