package com.example.numbersapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class NumberRequest {

    @NotEmpty(message = "numbers must not be empty")
    private List<@NotNull(message = "numbers must not contain null values") Integer> numbers;

}
