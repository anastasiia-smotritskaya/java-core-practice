package com.github.anastasiiasmotritskaya.javacore.oop;

/**
 * Представляет книгу в жанре техничевская литература в библиотечной системе.
 * Дополнительные поля: предмет (программирование, математика, физика),
 * уровень сложности (начинающий, продолжающий, продвинутый)
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class TechnicalBook extends Book {
    private String subject;
    private DifficultyLevel difficultyLevel;

    /**
     * Пустой конструктор класса Book необходим для десериализации json
     */
    public TechnicalBook() {
    }

    /**
     * Создаёт новую книгу с указанными параметрами.
     *
     * @param title           название книги (не может быть null или пустым)
     * @param author          автор книги (не может быть null или пустым)
     * @param year            год издания (должен быть между 1457 и текущим годом)
     * @param isbn            уникальный идентификатор книги, 13 символов (не может быть null или пустым)
     * @param subject         предмет (не может быть null или пустым)
     * @param difficultyLevel уровень сложности (может быть null)
     * @throws IllegalArgumentException если какой-либо параметр невалиден
     */
    public TechnicalBook(String title, String author, int year, String isbn, String subject, DifficultyLevel difficultyLevel) {
        super(title, author, year, isbn);
        validateSubject(subject);
        this.subject = subject.trim();
        this.difficultyLevel = difficultyLevel;
    }

    private void validateSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject field should not be null or empty. Enter the subject.");
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        validateSubject(subject);
        this.subject = subject.trim();
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public String toString() {
        return "TechnicalBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", isbn='" + getIsbn() + '\'' +
                ", status=" + getStatus() +
                ", currentBorrower='" + getCurrentBorrower() + '\'' +
                ", subject='" + subject + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
