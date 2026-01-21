package com.github.anastasiiasmotritskaya.javacore.oop;

import java.util.UUID;

/**
 * Представляет любой объект (книга, журнал, альбом и пр.) в библиотечной системе.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public abstract class LibraryItem {
    private final String inventoryNumber;

    public abstract String getDescription();

    public abstract String getTitle();

    protected LibraryItem() {
        inventoryNumber = UUID.randomUUID().toString();
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }
}
