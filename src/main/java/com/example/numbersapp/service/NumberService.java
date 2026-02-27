package com.example.numbersapp.service;

import com.example.numbersapp.dto.HistoryResponse;
import com.example.numbersapp.entity.CalculationHistory;
import com.example.numbersapp.exception.ResourceNotFoundException;
import com.example.numbersapp.repository.CalculationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NumberService {

    private final CalculationHistoryRepository historyRepository;

    public NumberService(CalculationHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public int sumPositive(List<Integer> nums) {
        int sum = 0;

        for (int n : nums) {
            if (n > 0) {
                sum += n;
            }
        }
        CalculationHistory history = new CalculationHistory(
                "SUM_POSITIVE",
                nums.toString(),
                String.valueOf(sum)
        );
        historyRepository.save(history);
        System.out.println("Saved history row: " + history);

        return sum;
    }

    public int max(List<Integer> nums) {
        int max = nums.get(0);

        for ( int n: nums) {
            if (n > max){
                max = n;
            }
        }
        return max;
    }

    public int countPositive(List<Integer> nums) {
        int count = 0;

        for (int n : nums) {
            if ( n > 0){
                count++;
            }
        }
        return count;
    }

    public double average(List<Integer> nums) {
        int sum = 0;

        for (int n : nums) {
            sum += n;
        }
        return (double) sum / nums.size();
    }

    public List<HistoryResponse> latestHistory() {
        return historyRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(h -> new HistoryResponse(
                        h.getOperation(),
                        h.getInputNumbers(),
                        h.getResult(),
                        h.getCreatedAt()
                ))
                .toList();
    }

    public HistoryResponse getHistoryById(Long id) {
        CalculationHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("History not found"));

        return new HistoryResponse(
                history.getOperation(),
                history.getInputNumbers(),
                history.getResult(),
                history.getCreatedAt()
        );
    }


}
