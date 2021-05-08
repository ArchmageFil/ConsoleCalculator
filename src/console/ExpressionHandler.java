package console;

import console.expression.ArithmeticExpression;
import console.expression.Expression;
import console.expression.RomanExpression;

/**
 * Класс принимает в себя введенную пользователем строку, разбивает на составные части,
 * проверяет на допустимость, тип системы счисления
 * и возвращает ответ.
 */
public class ExpressionHandler {
    private final String inputString;
    private Expression expression;

    public ExpressionHandler(String inputString) {
        this.inputString = inputString;
        decompose();
    }

    /**
     * Метод возвращает ответ в соответствующей запросу системе счисления.
     *
     * @return решение арифметического выражения в формате String.
     */
    public String giveAnswer() {
        return expression.calculate();
    }

    /**
     * Метод разбирает введенное пользователем выражение на составные части и проверяет нарушения синтаксиса.
     */
    private void decompose() {
        String[] strings = inputString.split(" ");

        if (strings.length != 3) {
            throw new UnsupportedOperationException(inputString + " не соответствует допустимому заданию, возможные " +
                    "варианты: a + b, a - b, a * b, a / b.");
        }

        if (isArabic(strings[0]) && isArabic(strings[2])) {
            expression = new ArithmeticExpression();
        } else if (!isArabic(strings[0]) && !isArabic(strings[2])) {
            expression = new RomanExpression(new ArithmeticExpression());
        } else throw new ArithmeticException("Оба числа должны быть в одной системе счисления");

        switch (strings[1]) {
            case "*" -> expression.setOperation(Operation.Multiply);
            case "/" -> expression.setOperation(Operation.Divide);
            case "+" -> expression.setOperation(Operation.Add);
            case "-" -> expression.setOperation(Operation.Delete);
            default -> throw new UnsupportedOperationException("Неподдерживаемая операция над числами.");
        }
        expression.setNumber1(strings[0]);
        expression.setNumber2(strings[2]);
    }

    /**
     * Метод проверяет является ли входящая строка числом в арабской системе счисления.
     *
     * @param input строка содержащая число.
     * @return true - если число арабское, false - если нет.
     */
    private boolean isArabic(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}
