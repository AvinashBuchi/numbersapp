package com.example.numbersapp.service;

import com.example.numbersapp.dto.HistoryResponse;
import com.example.numbersapp.entity.CalculationHistory;
import com.example.numbersapp.exception.ResourceNotFoundException;
import com.example.numbersapp.repository.CalculationHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NumberServiceTest {
    @Mock
    private CalculationHistoryRepository historyRepository;
    @InjectMocks
    private NumberService numberService;

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
    @Test
    void getHistoryById_returnHistoryResponse_whenIdExists() {
        CalculationHistory history = new CalculationHistory(
                "SUM_POSITIVE",
                "[1,2,3]",
                "6"
        );
        history.setCreatedAt(Instant.now());

        when(historyRepository.findById(1L))
                .thenReturn(Optional.of(history));

        HistoryResponse response = numberService.getHistoryById(1L);

        assertEquals("SUM_POSITIVE", response.getOperation());
        assertEquals("[1,2,3]", response.getInputNumbers());
        assertEquals("6", response.getResult());
    }

    @Test
    void getHistoryById_throwsException_whenIdDoesNotExist() {

        when(historyRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> numberService.getHistoryById(99L));
    }

}
