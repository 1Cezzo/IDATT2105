package com.github.Cezzo.vuecalculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.Cezzo.vuecalculator.model.CalculationResult;
import main.java.com.github.Cezzo.vuecalculator.service.CalculationService;

@RestController
public class CalculatorController {
    private CalculationService calculationService = new CalculationService();
    private double result;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String equation) {
        System.out.println(equation);
        result = calculationService.calculate(equation);
        return ResponseEntity.ok().body(Double.toString(result));
    }

    @PostMapping("/calculateJSON")
    public ResponseEntity<CalculationResult> calculateJSON(@RequestBody String equation) {
        System.out.println(equation);
        result = calculationService.calculate(equation);
        CalculationResult calculationResult = new CalculationResult(equation, result);
        return ResponseEntity.ok().body(calculationResult);
    }
}
