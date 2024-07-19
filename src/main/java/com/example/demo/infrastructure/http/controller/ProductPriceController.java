package com.example.demo.infrastructure.http.controller;

import com.example.demo.application.service.ProductPriceService;
import com.example.demo.infrastructure.http.api.PricesApi;
import com.example.demo.infrastructure.http.mapper.ProductPriceMapper;
import com.example.demo.infrastructure.http.model.ProductPrice;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductPriceController implements PricesApi {

    private final ProductPriceService service;

    public ProductPriceController(ProductPriceService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ProductPrice> getProductPrice(Integer brandId, Long productId,
        OffsetDateTime applicationDate) {
        return ResponseEntity.ok(ProductPriceMapper.INSTANCE
            .parse(service.getHighestPriorityPrice(brandId, productId, applicationDate)));
    }

}
