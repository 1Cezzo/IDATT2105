package com.github.Cezzo.vuecalculator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CalculationResult {
  private final String expression;
  private final double answer;
  private static final Logger LOGGER = LoggerFactory.getLogger(CalculationResult.class);

  public CalculationResult(String expression, double answer) {
      LOGGER.info("Handling request to calculate: " + expression);
      this.expression = expression;
      this.answer = answer;
  }

  public String getExpression() {
      return expression;
  }

  public double getAnswer() {
      return answer;
  }
}