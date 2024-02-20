package com.github.Cezzo.vuecalculator.model;

import jakarta.persistence.*;

@Entity
public class MyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;

    // Getters and setters
}
