package com.github.Cezzo.vuecalculator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calculation_results")
public class CalculationResultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String expression;
    private double answer;

    public CalculationResultEntity() {
    }

    public CalculationResultEntity(String expression, double answer) {
        this.expression = expression;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }
}
