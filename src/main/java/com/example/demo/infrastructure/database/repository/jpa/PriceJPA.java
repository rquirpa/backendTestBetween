package com.example.demo.infrastructure.database.repository.jpa;

import com.example.demo.infrastructure.database.entity.PriceEntity;
import java.time.OffsetDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceJPA extends JpaRepository<PriceEntity, Long> {

    @Query
    PriceEntity findTop1ByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        int brandId, long productId, OffsetDateTime applicationDate1, OffsetDateTime applicationDate2);

}
