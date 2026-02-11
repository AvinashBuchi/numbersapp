package com.example.numbersapp.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NumberService {

    public int sumPositive(List<Integer> nums) {
        int sum = 0;

        for (int n : nums) {
            if (n > 0) {
                sum += n;
            }
        }
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
}
