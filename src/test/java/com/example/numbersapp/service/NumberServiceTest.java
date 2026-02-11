package com.example.numbersapp.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberServiceTest {

    private final NumberService numberService = new NumberService();

    @Test
    void sumPositive_returnsSumOfOnlyPositiveNumbers() {
        int result = numberService.sumPositive(List.of(-2, 0, 3, 5));
        assertEquals(8, result);
    }

    @Test
    void countPositive_countsOnlyNumbersGreaterThanZero() {
        int result = numberService.countPositive(List.of(-1, 0, 3, 5));
        assertEquals(2, result);
    }

    @Test
    void max_returnsLargestNumber() {
        int result = numberService.max(List.of(3, 7, 2, 9));
        assertEquals(9, result);
    }

    @Test
    void average_returnsCorrectDoubleAverage() {
        double result = numberService.average(List.of(1, 2, 3, 4));
        assertEquals(2.5, result, 0.000001);
    }

    @Test
    void sumPositive_returnsZeroWhenAllNumbersAreNegative() {
        int result = numberService.sumPositive(List.of(-5, -1, -10));
        assertEquals(0, result);
    }

    @Test
    void average_returnsSameValueForSingleElement() {
        double result = numberService.average(List.of(5));
        assertEquals(5.0, result, 0.000001);
    }

    @Test
    void countPositive_returnsZeroWhenOnlyZerosPresent() {
        int result = numberService.countPositive(List.of(0, 0, 0));
        assertEquals(0, result);
    }

}
