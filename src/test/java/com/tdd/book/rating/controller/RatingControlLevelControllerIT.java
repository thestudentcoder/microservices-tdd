package com.tdd.book.rating.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RatingControlLevelControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNotFound_whenCustomerControlLevelAndBookIdIsNotProvided() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rcl/book/v1/read/eligibility/")
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequest_whenInvalidCustomerControlLevelAndBookIdIsProvided() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rcl/book/v1/read/eligibility/CONTROLXXXXX*/ID**")
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}