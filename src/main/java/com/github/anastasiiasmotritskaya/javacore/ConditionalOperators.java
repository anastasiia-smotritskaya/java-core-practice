package com.github.anastasiiasmotritskaya.javacore;

/**
 * Работа с условными операторами
 * Методы: перевод оценок из численного формата в буквенные, определитель времени года, калькулятор скидок
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class ConditionalOperators {
    /**
     * Конструктор по умолчанию для javadoc
     */
    private ConditionalOperators() {

    }

    /**
     * Конвертирует числовую оценку в буквенную
     * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: 0-59
     *
     * @param score количество баллов
     * @return char буквенная оценка
     * @throws IllegalArgumentException если:
     *                                  <ul>
     *                                   <li>score больше 100 баллов</li>
     *                                   <li>score меньше 0 баллов</li>
     *                                  </ul>
     * {@code char result = convertToGrade_if(95);} // A
     */
    public static char convertToGrade_if(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }

    public static char convertToGrade_switch(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        switch (score/10) {
            case 10:
            case 9:
                return 'A';
            case 8:
                return 'B';
            case 7:
                return 'C';
            case 6:
                return 'D';
            default:
                return 'F';
        }
    }

    public static char convertToGrade_switch_exp(int score) {
        if (score > 100 || score < 0)
            throw new IllegalArgumentException("The rating is incorrect! Rating range: 0 to 100 points");
        return switch (score) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> 'A';
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> 'B';
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> 'C';
            case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> 'D';
            default -> 'F';
        };
    }
}
