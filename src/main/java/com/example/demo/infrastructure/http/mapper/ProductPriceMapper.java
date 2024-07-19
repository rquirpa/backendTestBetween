package com.example.demo.infrastructure.http.mapper;

import com.example.demo.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductPriceMapper {

    ProductPriceMapper INSTANCE = Mappers.getMapper(ProductPriceMapper.class);

    com.example.demo.infrastructure.http.model.ProductPrice parse(ProductPrice obj);

}
