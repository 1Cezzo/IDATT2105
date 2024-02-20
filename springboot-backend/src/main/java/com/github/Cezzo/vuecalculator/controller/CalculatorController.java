package com.github.Cezzo.vuecalculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.Cezzo.vuecalculator.model.CalculationResult;
import main.java.com.github.Cezzo.vuecalculator.service.CalculationService;


@RestController
public class CalculatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);
    private final CalculationService calculationService = new CalculationService();
    private double result;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String equation) {
        LOGGER.info("Handling request to calculate: " + equation);
        System.out.println(equation);
        result = calculationService.calculate(equation);
        LOGGER.info("Result: " + result);
        return ResponseEntity.ok().body(Double.toString(result));
    }

    @PostMapping("/calculateJSON")
    public ResponseEntity<CalculationResult> calculateJSON(@RequestBody String equation) {
        LOGGER.info("Handling request to calculate: " + equation);
        System.out.println(equation);
        result = calculationService.calculate(equation);
        CalculationResult calculationResult = new CalculationResult(equation, result);
        LOGGER.info("Result: " + result);
        return ResponseEntity.ok().body(calculationResult);
    }
}
