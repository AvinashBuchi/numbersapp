package com.example.numbersapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
@Data
@Entity
public class CalculationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;

    @Column(length = 2000)
    private String inputNumbers;

    private String result;

    private Instant createdAt;

    public CalculationHistory() {
    }

    public CalculationHistory(String operation, String inputNumbers, String result) {
        this.operation = operation;
        this.inputNumbers = inputNumbers;
        this.result = result;
        this.createdAt = Instant.now();
    }
}