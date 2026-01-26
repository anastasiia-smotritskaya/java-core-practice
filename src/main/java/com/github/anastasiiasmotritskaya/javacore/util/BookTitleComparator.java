package com.github.anastasiiasmotritskaya.javacore.util;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;

import java.util.Comparator;

/**
 * Компаратор для книг по названию
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareToIgnoreCase(b2.getTitle());
    }
}
