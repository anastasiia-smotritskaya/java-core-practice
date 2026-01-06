package com.github.anastasiiasmotritskaya.javacore.data;

import java.util.Scanner;

/**
 * Консольный калькулятор
 * Методы: сложение, вычитание, умножение, деление
 *
 * @author Анастасия Смотрицкая
 * @version 1.0
 */
public class ConsoleCalculator {
    /**
     * Конструктор по умолчанию для javadoc
     */
    public ConsoleCalculator() {
    }

    Scanner scanner = new Scanner(System.in);

    public void start() {
        printWelcomeMessage();

        while (true) {
            String input = getUserInput();

            if (input.equalsIgnoreCase("exit")) break;

            try {
                Object[] parsedInput = parseInput(input);
                double a = (double) parsedInput[0];
                String operation = parsedInput[1].toString();
                double b = (double) parsedInput[2];

                System.out.println("Result: " + calculate(a, operation, b));

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        exit();
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I am your Console Calculator.");
        System.out.println("Please enter the operation as follows: NUMBER1 OPERATION(+, -, *, /) NUMBER2");
        System.out.println("For example: 5 + 3 or 10.5 / 2");
        System.out.println("Type 'exit' to quit.");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public static Object[] parseInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        String[] expression = input.trim().split("\\s+");

        if (expression.length != 3) {
            throw new IllegalArgumentException("Incorrect format. Use: NUMBER OPERATION NUMBER.");
        }

        double a, b;

        try {
            a = Double.parseDouble(expression[0]);
            b = Double.parseDouble(expression[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect number format. Use numbers like 5.5 or 10.");
        }

        String operation = expression[1];
        return new Object[]{a, operation, b};
    }

    public static double calculate(double a, String operation, double b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("You can't divide by zero.");
                } else {
                    return a / b;
                }
            default:
                throw new IllegalArgumentException("Unknown operation. Use +, -, *, or /.");
        }
    }

    public void exit() {
        System.out.println("See you!");
        scanner.close();
    }
}
