package com.example.demo.domain.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.domain.exception.NotFoundException;
import com.example.demo.domain.model.ProductPrice;
import com.example.demo.domain.repository.PriceRepository;
import com.github.javafaker.Faker;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductPriceServiceTest {

    private PriceRepository mockRepository;
    private ProductPriceService service;
    private final Faker faker = new Faker();

    @BeforeEach
    void setup() {
        mockRepository = mock(PriceRepository.class);
        service = new ProductPriceService(mockRepository);
    }

    @Test
    void getHighestPriorityPriceFound() {
        // arrange
        when(mockRepository.getHighestPriorityPrice(anyInt(), anyLong(), any()))
            .thenReturn(new ProductPrice());

        // act
        ProductPrice productPrice = service.getHighestPriorityPrice(faker.number().randomDigit(),
            faker.number().randomNumber(), OffsetDateTime.now());

        // assert
        assertNotNull(productPrice);
    }

    @Test
    void getHighestPriorityPriceNotFound() {
        // arrange
        when(mockRepository.getHighestPriorityPrice(anyInt(), anyLong(), any()))
            .thenReturn(null);

        // act
        NotFoundException thrown = assertThrows(NotFoundException.class,
            () -> service.getHighestPriorityPrice(faker.number().randomDigit(),
                faker.number().randomNumber(), OffsetDateTime.now()));

        // assert
        assertThat(thrown.getMessage(), containsString("not found"));
    }

}