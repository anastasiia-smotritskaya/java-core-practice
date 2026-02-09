package com.github.anastasiiasmotritskaya.javacore.multithreading;

/**
 * Счетчик
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Counter {
    private int count = 0;

    /**
     * Небезопасный метод для увеличения счетчика.
     * Может терять обновления при одновременном вызове из нескольких потоков
     * из-за race condition. Операция count++ не атомарна.
     *
     * @see #incrementSync() для потокобезопасной версии
     */
    public void increment() {
        count++;
    }

    /**
     * Потокобезопасный метод для увеличения счетчика
     */
    public synchronized void incrementSync() {
        count++;
    }

    public int getCounter() {
        return count;
    }
}
