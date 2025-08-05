package com.mactwo.mactwocommandservice.infrastructure.mapper;

import com.mactwo.dto.request.product.CreateProductRequest;
import com.mactwo.dto.response.ProductResponse;
import com.mactwo.mactwocommandservice.application.command.command.product.CreateProductCommand;
import com.mactwo.mactwocommandservice.domain.model.Product;
import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "variants", source = "variants")
    Product toProduct(CreateProductCommand request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductVariant toProductVariant(CreateProductRequest.VariantDTO variantDTO);

    List<ProductVariant> toProductVariantList(List<CreateProductRequest.VariantDTO> variantDTOs);

    ProductResponse toProductResponse(Product product);

}
