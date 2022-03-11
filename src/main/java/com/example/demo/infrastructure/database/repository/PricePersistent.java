package com.example.demo.infrastructure.database.repository;

import com.example.demo.domain.model.ProductPrice;
import com.example.demo.domain.repository.PriceRepository;
import com.example.demo.infrastructure.database.mapper.ProductPriceMapper;
import com.example.demo.infrastructure.database.repository.jpa.PriceJPA;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class PricePersistent implements PriceRepository {

    private final PriceJPA jpa;

    public PricePersistent(PriceJPA jpa) {
        this.jpa = jpa;
    }

    public ProductPrice getHighestPriorityPrice(int brandId, long productId,
        OffsetDateTime applicationDate) {
        return ProductPriceMapper.INSTANCE.parse(
            jpa.findTop1ByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate));
    }

}
