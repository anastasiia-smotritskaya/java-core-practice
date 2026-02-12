package com.github.anastasiiasmotritskaya.javacore.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Поставщик и лобработчик логов с использованием Threads
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class LogProcessor {
    private final BlockingQueue<String> producerQueue = new LinkedBlockingQueue<>();
    private final ConcurrentLinkedQueue<String> consumerQueue = new ConcurrentLinkedQueue<>();
    private static final int CONSUMER_THREADS = 5;
    private static final String POISON_PILL = "Poison pill";

    public ConcurrentLinkedQueue<String> getConsumerQueue() {
        return consumerQueue;
    }

    /**
     * Запускает поток-поставщик (producer), который генерирует логи и помещает их в общую очередь.
     *
     * @param count количество логов, которые нужно сгенерировать (включая poison pill).
     *              Фактически будет создано {@code count - 1} обычных сообщений и {@value #CONSUMER_THREADS} poison pill.
     * @throws IllegalArgumentException если {@code count < 1}
     */
    public void produceLogs(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count should not be less than 1");
        }

        Thread t = new Thread(() -> {
            for (int i = 0; i < count - 1; i++) {
                producerQueue.add("Log #" + i);
            }

            for (int i = 0; i < CONSUMER_THREADS; i++) {
                producerQueue.add(POISON_PILL);
            }
        });
        t.start();
    }

    /**
     * Запускает {@value #CONSUMER_THREADS} потоков-потребителей (consumers), которые забирают логи
     * из общей очереди, обрабатывают их и сохраняют в коллекцию результатов.
     *
     * @throws InterruptedException если текущий поток был прерван во время ожидания завершения consumer
     */
    public void consumeLogs() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < CONSUMER_THREADS; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    String currentElement;
                    try {
                        currentElement = producerQueue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (POISON_PILL.equals(currentElement)) break;
                    consumerQueue.add(currentElement);
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
}
