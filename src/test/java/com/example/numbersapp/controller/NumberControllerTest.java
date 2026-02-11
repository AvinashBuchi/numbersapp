package com.example.numbersapp.controller;

import com.example.numbersapp.service.NumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberController.class)
class NumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NumberService numberService;

    @Test
    void average_returnsCorrectResponse() throws Exception {

        when(numberService.average(java.util.List.of(1, 2, 3, 4)))
                .thenReturn(2.5);

        mockMvc.perform(
                        post("/numbers/average")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"numbers\":[1,2,3,4]}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.average").value(2.5));
    }

    @Test
    void average_returns400WhenNumbersIsEmpty() throws Exception {
        mockMvc.perform(
                        post("/numbers/average")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"numbers\":[]}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("numbers must not be empty"))
                .andExpect(jsonPath("$.status").value(400));
    }

}
