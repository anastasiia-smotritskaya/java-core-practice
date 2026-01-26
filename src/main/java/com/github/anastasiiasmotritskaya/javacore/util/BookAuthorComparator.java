package com.github.anastasiiasmotritskaya.javacore.util;

import com.github.anastasiiasmotritskaya.javacore.oop.Book;

import java.util.Comparator;

/**
 * Компаратор для книг по имени автора
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class BookAuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
    }
}
