package com.github.anastasiiasmotritskaya.javacore.oop;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.github.anastasiiasmotritskaya.javacore.util.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
        BookValidator.validateIsbn(isbn);
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
        BookValidator.validateIsbn(isbn);
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
        BookValidator.validateAuthor(author);
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
        BookValidator.validateTitle(title);
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
     * Возвращает основную информацию о книгах из разных категорий (Book, TechnicalBook, FictionBook)
     */
    public String getAllBooksBasicInfo() {
        StringBuilder booksInfo = new StringBuilder();
        for (Book book : books.values()) {
            if (book instanceof FictionBook) {
                booksInfo.append(String.format("Fiction: %s, genre: %s%n",
                        book.getTitle(), ((FictionBook) book).getGenre()));
                continue;
            }
            if (book instanceof TechnicalBook) {
                booksInfo.append(String.format("Technical: %s, subject: %s, difficulty Level: %s%n",
                        book.getTitle(), ((TechnicalBook) book).getSubject(), ((TechnicalBook) book).getDifficultyLevel()));
            } else {
                booksInfo.append(String.format("Book: %s%n", book.getTitle()));
            }
        }
        return String.valueOf(booksInfo);
    }

    /**
     * Ищет книги, изданные в указанный период
     *
     * @param fromYear начальный год (включительно)
     * @param toYear   конечный год (включительно)
     * @return List книги, удовлетворяющие условию
     * @throws IllegalArgumentException если введенные годы издания меньше 1457 или больше текущего
     *                                  или если конечный год меньше начального года
     */
    public List<Book> findBooksByYearRange(int fromYear, int toYear) {
        BookValidator.validateYear(fromYear);
        BookValidator.validateYear(toYear);
        LibraryValidator.validateYearRange(fromYear, toYear);

        List<Book> booksByYearRange = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getYear() >= fromYear && book.getYear() <= toYear) {
                booksByYearRange.add(book);
            }
        }
        return booksByYearRange;
    }

    /**
     * Возвращает множество уникальных авторов в библиотеке
     *
     * @return Set уникальные имена авторов
     */
    public Set<String> getAllUniqueAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book book : books.values()) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    /**
     * Подсчитывает количество книг по каждому статусу
     *
     * @return Map ({статус=количество)}
     */
    public Map<BookStatus, Integer> countBooksByStatus() {
        Map<BookStatus, Integer> statusMap = new HashMap<>();

        for (Book book : books.values()) {
            BookStatus status = book.getStatus();
            statusMap.put(status, statusMap.getOrDefault(status, 0) + 1);
        }

        return statusMap;
    }

    /**
     * Удаляет все книги указанного автора.
     * Использует итератор для безопасного удаления во время обхода коллекции.
     *
     * @param author автор, книги которого нужно удалить (не может быть null или пустым)
     * @return количество удаленных книг
     * @throws IllegalArgumentException если author равен null или пустой строке
     */
    public int removeBooksByAuthor(String author) {
        BookValidator.validateAuthor(author);

        int count = 0;

        Iterator<Book> iterator = books.values().iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает книги, отсортированные по году издания (от старых к новым)
     *
     * @return List отсортированный список
     */
    public List<Book> getBooksSortedByYear() {
        List<Book> bookList = new ArrayList<>(books.values());
        bookList.sort(new BookYearComparator());
        return bookList;
    }

    /**
     * Возвращает книги, отсортированные по имени автора
     *
     * @return List отсортированный список
     */
    public List<Book> getBooksSortedByAuthor() {
        List<Book> bookList = new ArrayList<>(books.values());
        bookList.sort(new BookAuthorComparator());
        return bookList;
    }

    /**
     * Возвращает книги, отсортированные по названию книги
     *
     * @return List отсортированный список
     */
    public List<Book> getBooksSortedByTitle() {
        List<Book> bookList = new ArrayList<>(books.values());
        bookList.sort(new BookTitleComparator());
        return bookList;
    }

    /**
     * Возвращает книги, отсортированные по названию книги, потом по имени автора, потом по году издания
     *
     * @return List отсортированный список
     */
    public List<Book> getBooksSortedByTitleThenAuthorThenYear() {
        List<Book> bookList = new ArrayList<>(books.values());
        Comparator<Book> complexComparator = Comparator.comparing(Book::getTitle).
                thenComparing(Book::getAuthor).thenComparingInt(Book::getYear);
        bookList.sort(complexComparator);
        return bookList;
    }
}
