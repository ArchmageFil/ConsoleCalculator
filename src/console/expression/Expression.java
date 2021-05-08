package console.expression;

import console.Operation;

public interface Expression {
    String calculate();

    void setNumber1(String number1);

    void setNumber2(String number2);

    void setOperation(Operation operation);
}
