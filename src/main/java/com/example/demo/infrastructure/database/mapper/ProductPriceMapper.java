package com.example.demo.infrastructure.database.mapper;

import com.example.demo.infrastructure.database.entity.PriceEntity;
import com.example.demo.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductPriceMapper {

    ProductPriceMapper INSTANCE = Mappers.getMapper(ProductPriceMapper.class);

    ProductPrice parse(PriceEntity obj);

}
