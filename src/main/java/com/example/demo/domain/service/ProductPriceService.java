package com.example.demo.domain.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.example.demo.domain.exception.NotFoundException;
import com.example.demo.domain.repository.PriceRepository;
import com.example.demo.domain.model.ProductPrice;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceService {

    private final PriceRepository repository;

    public ProductPriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public ProductPrice getHighestPriorityPrice(Integer brandId, Long productId,
        OffsetDateTime applicationDate) {
        ProductPrice productPrice = repository.getHighestPriorityPrice(brandId, productId, applicationDate);

        if (isEmpty(productPrice)) {
            throw new NotFoundException("Price not found");
        }

        return productPrice;
    }

}
