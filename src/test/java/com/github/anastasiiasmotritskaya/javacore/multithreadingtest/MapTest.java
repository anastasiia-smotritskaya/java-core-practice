package com.github.anastasiiasmotritskaya.javacore.multithreadingtest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Java Core")
@Feature("Multithreading")
@Story("Map VS Synchronized map VS Concurrent map")
public class MapTest {
    private static final int THREADS = 10;
    private static final int ITERATIONS = 1000;
    private static final int TOTAL = 10_000;

    @Test
    @DisplayName("Plain HashMap loses data in concurrent writes")
    public void hashMapLosesDataTest() throws InterruptedException {
        Map<String, Integer> map = new HashMap<>();
        mapCreationWithThreads(map);
        assertTrue(map.size() < TOTAL);
    }

    @Test
    @DisplayName("SynchronizedMap preserves all data")
    public void synchronizedMapPreservesDataTest() throws InterruptedException {
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        mapCreationWithThreads(synchronizedMap);

        assertEquals(TOTAL, synchronizedMap.size());
        mapChecking(synchronizedMap);
    }

    @Test
    @DisplayName("ConcurrentHashMap preserves all data")
    public void concurrentMapPreservesDataTest() throws InterruptedException {
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        mapCreationWithThreads(concurrentMap);

        assertEquals(TOTAL, concurrentMap.size());
        mapChecking(concurrentMap);
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @DisplayName("Check time and size of the different types of map if you write and read it via several threads")
    @MethodSource("mapDataProvider")
    public void mapPerformanceTest(Map<String, Integer> map, String description) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            final int threadId = i;
            Thread t = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    int value = threadId * MapTest.ITERATIONS + j;
                    String key = "Iteration № " + value;
                    map.put(key, value);
                    map.get(key);
                }
            });
            threads.add(t);
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Time: " + duration + " ms");
        System.out.println("Map size: " + map.size());
    }

    private static Stream<Arguments> mapDataProvider() {
        return Stream.of(
                Arguments.of(new HashMap<>(), "Plain map"),
                Arguments.of(Collections.synchronizedMap(new HashMap<>()), "Synchronized map"),
                Arguments.of(new ConcurrentHashMap<>(), "Concurrent map")
        );
    }

    /**
     * Заполняет уже созданный Map (map, synchronizedMap или concurrentMap) значениями с использованием Threads
     * Вспомогательный метод
     */
    private static void mapCreationWithThreads(Map<String, Integer> map) throws InterruptedException {
        List<Thread> threads = new ArrayList<>(MapTest.THREADS);

        for (int i = 0; i < MapTest.THREADS; i++) {
            final int threadId = i;
            Thread t = new Thread(() -> {
                for (int j = 0; j < MapTest.ITERATIONS; j++) {
                    int value = threadId * MapTest.ITERATIONS + j;
                    String key = "Iteration № " + value;
                    map.put(key, value);
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
    }

    /**
     * Проверяет корректность сохраненных в Map (map, synchronizedMap или concurrentMap) значений
     * Вспомогательный метод
     */
    private static void mapChecking(Map<String, Integer> map) {
        for (int i = 0; i < THREADS; i++) {
            for (int j = 0; j < ITERATIONS; j++) {
                int value = i * ITERATIONS + j;
                String key = "Iteration № " + value;
                assertEquals(value, map.get(key));
            }
        }
    }
}
