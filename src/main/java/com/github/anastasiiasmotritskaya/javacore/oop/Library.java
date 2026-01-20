package com.github.anastasiiasmotritskaya.javacore.oop;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Представляет библиотеку книг с возможностью поиска, добавления, удаления,
 * а также сохранения и загрузки из JSON файла.
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class Library {

    private Map<String, Book> books;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Создаёт новую пустую библиотеку.
     *
     */
    public Library() {
        this.books = new HashMap<>();
    }

    /**
     * Создаёт новую библиотеку с указанным списком книг.
     *
     * @param books список книг (может быть пустым, ключ - ISBN)
     */
    public Library(Map<String, Book> books) {
        this.books = new HashMap<>(books);
    }

    /**
     * Добавляет книгу в библиотеку.
     *
     * @param book книга для добавления (не может быть null)
     * @throws IllegalArgumentException если книга с таким ISBN уже существует в библиотеке
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException(String.format("Key '%s' already exists with value: %s", book.getIsbn(),
                    books.get(book.getIsbn()).toString()));
        }
        books.put(book.getIsbn(), book);
    }

    /**
     * Удаляет книгу из бибилиотеки.
     *
     * @param isbn книга для удаления (проходит валидацию)
     * @throws IllegalArgumentException если книги с таким ISBN в библиотеке нет
     */
    public void deleteBook(String isbn) {
        validateIsbn(isbn);
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException(String.format("There is no book with this number (%s). Please check the number.", isbn));
        }
        books.remove(isbn);
    }

    /**
     * Поиск книги в библиотеке по ISBN.
     *
     * @param isbn книга для поиска (проходит валидацию)
     * @return Book - книгу с введенным ISBN
     * @throws IllegalArgumentException если книги с таким ISBN в библиотеке нет
     */
    public Book findBookByISBN(String isbn) {
        validateIsbn(isbn);
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException(String.format("There is no book with this number (%s). Please check the number.", isbn));
        }
        return books.get(isbn);
    }

    /**
     * Поиск книги в библиотеке по автору.
     *
     * @param author автор, чьи книги необходимо найти (проходит валидацию)
     * @return List - список книг данного автора
     * Если книг этого автора в библиотеке нет, возвращает пустой список
     */
    public List<Book> findBookByAuthor(String author) {
        validateAuthor(author);
        List<Book> thisAuthorWrittenBooks = new ArrayList<>();
        for (Map.Entry<String, Book> book : books.entrySet()) {
            if (book.getValue().getAuthor().equalsIgnoreCase(author)) {
                thisAuthorWrittenBooks.add(book.getValue());
            }
        }
        return thisAuthorWrittenBooks;
    }

    /**
     * Поиск книги в библиотеке по названию.
     *
     * @param title навзание книги, которую необходимо найти в библиотеке (проходит валидацию)
     * @return List - список книг с введенным названием
     * Если книг с таким названием в библиотеке нет, возвращает пустой список
     */
    public List<Book> findBookByTitle(String title) {
        validateTitle(title);
        List<Book> thisTitleBooks = new ArrayList<>();
        for (Map.Entry<String, Book> book : books.entrySet()) {
            if (book.getValue().getTitle().equalsIgnoreCase(title)) {
                thisTitleBooks.add(book.getValue());
            }
        }
        return thisTitleBooks;
    }

    /**
     * Возвращает список всех книг в библиотеке.
     *
     * @return Map - список всех книг в библиотеке
     * Если книг с таким названием в библиотеке нет, возвращает пустой список
     */
    public Map<String, Book> getAllBooks() {
        return Map.copyOf(books);
    }

    public void saveToExistingJsonFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        File file = new File(filePath.trim());

        if (!file.exists()) {
            throw new IOException(String.format("File does not exist: '%s'", filePath));
        }

        mapper.writeValue(file, books);
    }

    /**
     * Сохраняет библиотеку в новый файл в формате json
     *
     * @param filePath путь, по которому следует создать новый файл, в котором следует сохранить библиотеку
     * @throws IllegalArgumentException если filePath null или empty
     * @throws IOException              если файл по данному пути уже существует
     */
    public void saveToNewJsonFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        File file = new File(filePath.trim());

        if (file.exists()) {
            throw new IOException(String.format("File already exists: '%s'. " +
                    "Use saveToExistingJsonFile() or choose different name.", filePath));
        }

        Path path = file.toPath();
        Files.createDirectories(path.getParent());

        mapper.writeValue(file, books);
    }

    /**
     * Сохраняет библиотеку из файла в формате json
     *
     * @param filePath путь, по которому которому находится файл в формате json, откуда следует загрузить бибилиотеку
     * @throws IllegalArgumentException если filePath null или empty
     * @throws IOException              если файла по данному пути не существует, проблема в структуре json
     *                                  или возникла проблема при чтении
     * @throws JsonParseException       если проблема с синтаксисом json
     * @throws MismatchedInputException если поля не совпадают (title, author, year, isbn)
     */
    public void loadFromJsonFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        File file = new File(filePath.trim());

        if (!file.exists()) {
            throw new IOException(String.format("File does not exist: '%s'", filePath));
        }

        if (file.length() == 0) {
            this.books = new HashMap<>();
            return;
        }
        try {
            this.books = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (JsonParseException e) {
            throw new IOException(String.format("Invalid JSON syntax in file '%s': %s", filePath, e.getMessage()), e);
        } catch (MismatchedInputException e) {
            if (e.getMessage().contains("Cannot construct instance of 'Book'")) {
                throw new IOException(
                        String.format(
                                "Missing required fields in book data in file '%s'." +
                                        "Ensure all books have 'title', 'author', 'year' and 'isbn'.",
                                filePath),
                        e
                );
            }
            throw new IOException(
                    String.format("Unexpected JSON structure in file '%s': %s", filePath, e.getMessage()),
                    e
            );
        } catch (IOException e) {
            throw new IOException(String.format("Error reading file '%s': %s", filePath, e.getMessage()), e);
        }
    }

    /**
     * Валидация ISBN - The International Standard Book Number.
     *
     * @throws IllegalArgumentException если isbn null, empty, длине не равна 13 символам
     *                                  или содержит специальные символы
     */
    private void validateIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("The International Standard Book Number field must not be empty. " +
                    "Enter the International Standard Book Number.");
        }

        if (isbn.trim().length() != 13) {
            throw new IllegalArgumentException(String.format("The International Standard Book Number was entered incorrectly (ISBN-13). " +
                    "You entered: %s. Please enter the correct International Standard Book Number (13 symbols).", isbn));
        }

        if (!isbn.trim().matches("[A-Za-z0-9]{13}")) {
            throw new IllegalArgumentException(
                    "ISBN must contain only letters and digits. Got: " + isbn.trim());
        }
    }

    /**
     * Валидация имени автора.
     *
     * @throws IllegalArgumentException если название имя автора null или empty
     */
    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("The author field must not be empty. Enter the author's name.");
        }
    }

    /**
     * Валидация название книги.
     *
     * @throws IllegalArgumentException если название книги null или empty
     */
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title field should not be null or empty. Enter the title.");
        }
    }
}
