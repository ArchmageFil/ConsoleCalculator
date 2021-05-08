package console;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Перечисление римских чисел.
 * Стандартных и тех у которых меньшее число вычитается из большего, чтобы проще было переводить.
 */
public enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Метод для получения списка римских цифр в порядке от большего к меньшему.
     */
    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Метод переводит число в римской системе счисления в арабскую.
     *
     * @param input строка содержащая римское число.
     * @return целое арабское число сконвертированное из римского.
     * @throws IllegalArgumentException в случае невозможности конвертации.
     */
    public static String romanToInt(String input) {
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

        return String.valueOf(result);
    }

    /**
     * Метод принимает на вход арабское число и переводит его в римскую систему счисления.
     *
     * @param input целое десятичное число в диапазоне 0 - 4000.
     * @return Римское число в формате String.
     * @throws IllegalArgumentException в случае выхода за диапазон.
     */
    public static String intToRoman(String input) {
        int number = Integer.parseInt(input);
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
