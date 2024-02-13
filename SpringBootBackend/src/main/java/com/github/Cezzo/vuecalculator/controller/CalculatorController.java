package com.github.Cezzo.vuecalculator.controller;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public String calculate(@RequestBody String equation) {
      System.out.println(equation);
      Expression expression = new ExpressionBuilder(equation)
              .build();
      return "" + expression.evaluate();
    }
}
