package com.example.numbersapp.controller;

import com.example.numbersapp.dto.*;
import com.example.numbersapp.service.NumberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/numbers")
public class NumberController {

    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/sum-positive")
    public ResponseEntity<SumResponse> sumPositive(@Valid @RequestBody NumberRequest request) {
       // int x = 1 / 0;
        int sum = numberService.sumPositive(request.getNumbers());
        return ResponseEntity.ok(new SumResponse(sum));
    }

    @PostMapping("/max")
    public ResponseEntity<MaxResponse> max(@Valid @RequestBody NumberRequest request) {
        int maxValue = numberService.max(request.getNumbers());
        return ResponseEntity.ok(new MaxResponse(maxValue));
    }

    @PostMapping("/count-positive")
    public ResponseEntity<CountResponse> countPositive(@Valid @RequestBody NumberRequest request) {
        int count = numberService.countPositive(request.getNumbers());
        return ResponseEntity.ok(new CountResponse(count));
    }

    @GetMapping("/ping")
    public ResponseEntity<PingResponse> ping() {
        return ResponseEntity.ok(new PingResponse("ok"));
    }

    @PostMapping("/average")
    public ResponseEntity<AverageResponse> average(@Valid @RequestBody NumberRequest request) {
        log.info("Calculating average for number: {}", request.getNumbers());
       double avg =  numberService.average(request.getNumbers());

       log.info("Calculated average: {}", avg);
       return ResponseEntity.ok(new AverageResponse(avg));
    }

}
