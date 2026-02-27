package com.example.numbersapp.repository;

import com.example.numbersapp.entity.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculationHistoryRepository
        extends JpaRepository<CalculationHistory, Long> {

    List<CalculationHistory> findTop10ByOrderByCreatedAtDesc();
}