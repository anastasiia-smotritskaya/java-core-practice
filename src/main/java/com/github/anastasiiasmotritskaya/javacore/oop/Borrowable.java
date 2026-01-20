package com.github.anastasiiasmotritskaya.javacore.oop;

/**
 * Интерфейс для объектов, которые можно брать взаймы в библиотеке.
 * Определяет контракт для операций выдачи и возврата.
 */
public interface Borrowable {
    /**
     * Выдаёт объект читателю.
     * @param borrowerName имя читателя
     * @throws IllegalStateException если объект уже выдан или забронирован
     */
    void borrow(String borrowerName);

    /**
     * Возвращает объект в библиотеку.
     * @throws IllegalStateException если объект не был выдан
     */
    void returnBook();

    /**
     * Проверяет доступен ли объект для выдачи.
     * @return true если объект доступен (статус AVAILABLE)
     */
    boolean isAvailable();

    /**
     * Возвращает имя текущего держателя книги.
     * @return имя читателя или null если книга доступна
     */
    String getCurrentBorrower();
}
