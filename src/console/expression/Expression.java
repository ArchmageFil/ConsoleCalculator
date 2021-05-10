package console.expression;

import console.Operation;

/**
 * Интерфейс определяющий основное поведение выражений для сознадия оболочек в разных счисления.
 */
public interface Expression {
    String calculate();

    void setNumber1(String number1);

    void setNumber2(String number2);

    void setOperation(Operation operation);
}
