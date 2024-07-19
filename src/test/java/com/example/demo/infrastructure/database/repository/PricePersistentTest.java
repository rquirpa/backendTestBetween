package com.example.demo.infrastructure.database.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.date.DateUtil;
import com.example.demo.domain.entity.ProductPrice;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PricePersistentTest {

    private static final int ZARA_ID = 1;
    private static final long PRODUCT_ID = 35455;

    @Autowired
    private PricePersistent pricePersistent;

    @Test
    void getHighestPriorityPriceFoundByUpperLimit() {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-12-31T23:59:59");

        // act
        ProductPrice productPrice = pricePersistent
            .getHighestPriorityPrice(ZARA_ID, PRODUCT_ID, applicationDate);

        // assert
        assertNotNull(productPrice);
    }

    @Test
    void getHighestPriorityPriceNotFoundByUpperLimit() {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2021-01-01T00:00:00");

        // act
        ProductPrice productPrice = pricePersistent
            .getHighestPriorityPrice(ZARA_ID, PRODUCT_ID, applicationDate);

        // assert
        assertNull(productPrice);
    }

    @Test
    void getHighestPriorityPriceFoundByLowerLimit() {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-14T00:00:00");

        // act
        ProductPrice productPrice = pricePersistent
            .getHighestPriorityPrice(ZARA_ID, PRODUCT_ID, applicationDate);

        // assert
        assertNotNull(productPrice);
    }

    @Test
    void getHighestPriorityPriceNotFoundByLowerLimit() {
        // arrange
        OffsetDateTime applicationDate = DateUtil.parse("2020-06-13T23:59:59");

        // act
        ProductPrice productPrice = pricePersistent
            .getHighestPriorityPrice(ZARA_ID, PRODUCT_ID, applicationDate);

        // assert
        assertNull(productPrice);
    }

}