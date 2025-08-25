package com.mactwo.query.mapper;

import com.mactwo.document.dto.response.OrderResponse;
import com.mactwo.query.document.OrderDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderQueryMapper {
    @Mapping(source = "orderId", target = "id")
    OrderResponse toResponse(OrderDocument document);
}