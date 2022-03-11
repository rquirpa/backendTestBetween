package com.example.demo.infrastructure.http.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.date.DateUtil;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ProductPriceControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final int ZARA_ID = 1;
    private static final long PRODUCT_ID = 35455;
    private static final String ENDPOINT = "/prices/brand/{brandId}/product/{productId}";

    @Test
    void getPriceWithStartDateLowerThanApplicationDate() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-14T10:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rateId", equalTo(1)));
    }

    @Test
    void getPriceWithEndDateHigherThanApplicationDate() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-14T21:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rateId", equalTo(1)));
    }

    @Test
    void getPriceWithHighestPrioritySameDay() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-14T16:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rateId", equalTo(2)));
    }

    @Test
    void getPriceWithHighestPriorityDifferentDays() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-15T10:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rateId", equalTo(3)));
    }

    @Test
    void getPriceWithHighestPriorityInLongIntervals() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-16T21:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rateId", equalTo(4)));
    }

    @Test
    void priceNotFound() throws Exception {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-01T00:00:00");

        // act
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT, ZARA_ID, PRODUCT_ID)
            .queryParam("applicationDate", applicationDate.toString())
        );

        // assert
        resultActions
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error", is(notNullValue())));
    }

}