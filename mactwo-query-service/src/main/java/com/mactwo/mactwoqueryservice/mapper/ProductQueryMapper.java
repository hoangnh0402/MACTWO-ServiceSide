package com.mactwo.query.mapper;

import com.mactwo.document.dto.response.ProductResponse;
import com.mactwo.query.document.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductQueryMapper {

    @Mapping(source = "productId", target = "id")
    ProductResponse toResponse(ProductDocument document);
}