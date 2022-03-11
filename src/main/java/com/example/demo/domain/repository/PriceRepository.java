package com.example.demo.domain.repository;

import com.example.demo.domain.model.ProductPrice;
import java.time.OffsetDateTime;

public interface PriceRepository {

    ProductPrice getHighestPriorityPrice(int brandId, long productId,
        OffsetDateTime applicationDate);

}
