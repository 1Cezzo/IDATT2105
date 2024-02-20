package main.java.com.github.Cezzo.vuecalculator.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculationService {
    public double calculate(String equation) {
        Expression expression = new ExpressionBuilder(equation).build();
        return expression.evaluate();
    }
}
