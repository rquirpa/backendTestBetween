package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class ProductPrice {

    private long id;
    private int brandId;
    private long productId;
    private BigDecimal price;
    private int rateId;
    private String currency;
    private int priority;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

}
