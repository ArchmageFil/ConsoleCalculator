package console.expression;

import console.Operation;
import console.RomanNumeral;
import console.expression.ArithmeticExpression;
import console.expression.Expression;

public class RomanExpression implements Expression {
    final private ArithmeticExpression wrapped;

    public RomanExpression(ArithmeticExpression arithmeticExpression) {
        wrapped = arithmeticExpression;
    }

    @Override
    public String calculate() {

        return RomanNumeral.intToRoman(wrapped.calculate());
    }

    @Override
    public void setNumber1(String number1) {
        wrapped.setNumber1(RomanNumeral.romanToInt(number1));
    }

    @Override
    public void setNumber2(String number2) {
        wrapped.setNumber2(RomanNumeral.romanToInt(number2));
    }

    @Override
    public void setOperation(Operation operation) {
        wrapped.setOperation(operation);
    }
}
