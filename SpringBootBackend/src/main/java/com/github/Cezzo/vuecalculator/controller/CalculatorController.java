package com.github.Cezzo.vuecalculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.Cezzo.vuecalculator.model.CalculationResult;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String equation) {
        System.out.println(equation);
        Expression expression = new ExpressionBuilder(equation).build();
        double result = expression.evaluate();
        return ResponseEntity.ok(Double.toString(result));
    }

    @PostMapping("/calculateJSON")
    public ResponseEntity<CalculationResult> calculateJSON(@RequestBody String equation) {
        System.out.println(equation);
        Expression expression = new ExpressionBuilder(equation).build();
        double result = expression.evaluate();
        CalculationResult calculationResult = new CalculationResult(equation, result);
        return ResponseEntity.ok().body(calculationResult);
    }
}
