package main.java.com.github.Cezzo.vuecalculator.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationService.class);

    public double calculate(String equation) {
        LOGGER.info("Handling request to calculate: " + equation);
        Expression expression = new ExpressionBuilder(equation).build();
        LOGGER.info("Result: " + expression.evaluate());
        return expression.evaluate();
    }
}
