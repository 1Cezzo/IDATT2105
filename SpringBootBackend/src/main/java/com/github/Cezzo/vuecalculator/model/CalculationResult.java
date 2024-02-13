package com.github.Cezzo.vuecalculator.model;

public class CalculationResult {
  private final String expression;
  private final double answer;

  public CalculationResult(String expression, double answer) {
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