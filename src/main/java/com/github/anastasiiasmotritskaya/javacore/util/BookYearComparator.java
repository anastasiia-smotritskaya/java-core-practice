package com.github.anastasiiasmotritskaya.javacore.util;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;

import java.util.Comparator;

/**
 * Компаратор для книг по году издания
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b1.getYear(), b2.getYear());
    }
}
