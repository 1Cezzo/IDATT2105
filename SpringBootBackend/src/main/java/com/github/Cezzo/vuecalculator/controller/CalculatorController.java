package com.github.Cezzo.vuecalculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Cezzo.vuecalculator.model.CalculationResultEntity;
import com.github.Cezzo.vuecalculator.model.CalculationResultRepository;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculationResultRepository calculationResultRepository;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String equation) {
        System.out.println(equation);
        Expression expression = new ExpressionBuilder(equation).build();
        double result = expression.evaluate();
        return ResponseEntity.ok(Double.toString(result));
    }
    
    @PostMapping("/calculateJSON")
    public ResponseEntity<CalculationResultEntity> calculateJSON(@RequestBody String equation) {
        System.out.println(equation);
        Expression expression = new ExpressionBuilder(equation).build();
        double result = expression.evaluate();
        CalculationResultEntity calculationResult = new CalculationResultEntity(equation, result);
        calculationResultRepository.save(calculationResult);
        return ResponseEntity.ok().body(calculationResult);
    }

    @GetMapping("/calculations")
    public ResponseEntity<List<CalculationResultEntity>> getAllCalculations() {
        List<CalculationResultEntity> calculations = calculationResultRepository.findAll();
        return ResponseEntity.ok().body(calculations);
    }
}
