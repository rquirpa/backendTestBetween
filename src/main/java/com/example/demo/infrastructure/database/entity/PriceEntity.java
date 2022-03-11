package com.example.demo.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int brandId;

    @Column(nullable = false)
    private long productId;

    @Column(length = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int rateId;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private OffsetDateTime startDate;

    @Column(nullable = false)
    private OffsetDateTime endDate;

}
