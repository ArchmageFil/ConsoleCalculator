package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * Точка входа в калькулятор.
     * @param args игнорируются.
     * @throws IOException - при проблеме считывания с консоли.
     * @throws IllegalArgumentException - при неверно введенных числах или выходе за диапазон римских чисел.
     * @throws UnsupportedOperationException - при неверно введенной операции.
     * @throws ArithmeticException - при вводе одновременно арабских и римских чисел.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ExpressionHandler handler;
        String input;

        while (!(input = reader.readLine()).equalsIgnoreCase("break")) {
            handler = new ExpressionHandler(input);
            System.out.println(handler.giveAnswer());
        }

        reader.close();
    }
}
