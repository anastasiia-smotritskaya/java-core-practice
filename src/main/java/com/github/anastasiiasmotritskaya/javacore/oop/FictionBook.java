package com.github.anastasiiasmotritskaya.javacore.oop;

/**
 * Представляет книгу в жанре художественная литература литература в библиотечной системе.
 * Дополнительное поле: жанр
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class FictionBook extends Book {
    private String genre;

    /**
     * Пустой конструктор класса Book необходим для десериализации json
     */
    public FictionBook() {
    }

    /**
     * Создаёт новую книгу с указанными параметрами.
     *
     * @param title  название книги (не может быть null или пустым)
     * @param author автор книги (не может быть null или пустым)
     * @param year   год издания (должен быть между 1457 и текущим годом)
     * @param isbn   уникальный идентификатор книги, 13 символов (не может быть null или пустым)
     * @param genre  жанр (не может быть null или пустым)
     * @throws IllegalArgumentException если какой-либо параметр невалиден
     */
    public FictionBook(String title, String author, int year, String isbn, String genre) {
        super(title, author, year, isbn);
        validateGenre(genre);
        this.genre = genre.trim();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        validateGenre(genre);
        this.genre = genre.trim();
    }

    private void validateGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("The genre field must not be null or empty. Enter the genre.");
        }
    }

    @Override
    public String toString() {
        return "FictionBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", isbn='" + getIsbn() + '\'' +
                ", status=" + getStatus() +
                ", currentBorrower='" + getCurrentBorrower() + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
