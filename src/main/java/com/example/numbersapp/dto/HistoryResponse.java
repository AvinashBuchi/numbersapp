package com.example.numbersapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
@Data
@AllArgsConstructor
public class HistoryResponse {

    private String operation;
    private String inputNumbers;
    private String result;
    private Instant createdAt;
}
