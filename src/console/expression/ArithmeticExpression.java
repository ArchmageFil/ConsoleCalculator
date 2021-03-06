package console.expression;

import console.Operation;

/**
 * Объект содержащий в себе арифметическое выражение и возвращающий его результат.
 */
public class ArithmeticExpression implements Expression {
    private int number1;
    private int number2;
    private Operation operation;

    /**
     * Метод вычисляющий решение арифметического выражения.
     *
     * @return ответ в формате int.
     * @throws UnsupportedOperationException если не задана операция над числами.
     */
    public String calculate() {
        switch (operation) {
            case Add -> {
                return String.valueOf(Calculator.add(number1, number2));
            }
            case Multiply -> {
                return String.valueOf(Calculator.multiply(number1, number2));
            }
            case Delete -> {
                return String.valueOf(Calculator.delete(number1, number2));
            }
            case Divide -> {
                return String.valueOf(Calculator.divide(number1, number2));
            }
            default -> throw new UnsupportedOperationException("Неподдерживаемая операция над числами.");
        }
    }

    /**
     * Проверяет находится ли число в допустимом по условию задачи диапазоне и сохраняет в объекте.
     *
     * @param number1 целое число в диапазоне 1 - 10 включительно.
     * @throws IllegalArgumentException в случае выхода числа за диапазон.
     */
    public void setNumber1(String number1) {
        this.number1 = Integer.parseInt(number1);
        if (this.number1 > 10 || this.number1 < 1) {
            throw new IllegalArgumentException(number1 + " выходит за допустимый диапазон (1-10)");

        }

    }

    /**
     * Проверяет находится ли число в допустимом по условию задачи диапазоне и сохраняет в объекте.
     *
     * @param number2 целое число в диапазоне 1 - 10 включительно.
     * @throws IllegalArgumentException в случае выхода числа за диапазон.
     */
    public void setNumber2(String number2) {
        this.number2 = Integer.parseInt(number2);
        if (this.number2 > 10 || this.number2 < 1) {
            throw new IllegalArgumentException(number1 + " выходит за допустимый диапазон (1-10)");

        }
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Статический класс содержащий методы, выполняющие арифметические операции с целыми числами: сложение, вычитание
     * умножение и целочисленное деление.
     */
    private static class Calculator {
        static int add(int x, int y) {
            return x + y;
        }

        static int delete(int x, int y) {
            return x - y;
        }

        static int multiply(int x, int y) {
            return x * y;
        }

        static int divide(int x, int y) {
            return x / y;
        }
    }
}
