package console;

import java.util.List;

/**
 * Класс принимает в себя введенную пользователем строку, разбивает на составные части,
 * проверяет на допустимость, тип системы счисления
 * и возвращает ответ.
 */
public class ExpressionHandler {
    private final String inputString;
    private final ArithmeticExpression expression = new ArithmeticExpression();
    private boolean arabic;

    public ExpressionHandler(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Метод возвращает ответ в соответствующей запросу системе счисления.
     * @return решение арифметического выражения в формате String.
     */
    public String giveAnswer()  {
        decompose();
        if (arabic) {
            return String.valueOf(expression.calculate());
        } else {
            return intToRoman(expression.calculate());
        }
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
            arabic = true;
        } else if (!isArabic(strings[0]) && !isArabic(strings[2])) {
            arabic = false;
        } else throw new ArithmeticException("Оба числа должны быть в одной системе счисления");

        switch (strings[1]) {
            case "*" -> expression.setOperation(Operation.Multiply);
            case "/" -> expression.setOperation(Operation.Divide);
            case "+" -> expression.setOperation(Operation.Add);
            case "-" -> expression.setOperation(Operation.Delete);
            default -> throw new UnsupportedOperationException("Неподдерживаемая операция над числами.");
        }

        if (arabic) {
            expression.setNumber1(Integer.parseInt(strings[0]));
            expression.setNumber2(Integer.parseInt(strings[2]));
        } else {
            expression.setNumber1(romanToInt(strings[0]));
            expression.setNumber2(romanToInt(strings[2]));
        }
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

    /**
     * Метод переводит число в римской системе счисления в арабскую.
     *
     * @param input строка содержащая римское число.
     * @return целое арабское число сконвертированное из римского.
     * @throws IllegalArgumentException в случае невозможности конвертации.
     */
    private int romanToInt(String input)  {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " не может быть сконвертировано в римское число");
        }

        return result;
    }

    /**
     * Метод принимает на вход арабское число и переводит его в римскую систему счисления.
     *
     * @param number целое десятичное число в диапазоне 0 - 4000.
     * @return Римское число в формате String.
     * @throws IllegalArgumentException в случае выхода за диапазон.
     */
    private String intToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " выходит за диапазон римских чисел");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
