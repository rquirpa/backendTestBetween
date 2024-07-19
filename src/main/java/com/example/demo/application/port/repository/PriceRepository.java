package com.example.demo.application.port.repository;

import com.example.demo.domain.entity.ProductPrice;
import java.time.OffsetDateTime;

public interface PriceRepository {

    ProductPrice getHighestPriorityPrice(int brandId, long productId,
        OffsetDateTime applicationDate);

}
