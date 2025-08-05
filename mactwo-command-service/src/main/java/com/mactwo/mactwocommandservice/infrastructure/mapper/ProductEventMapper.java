package com.mactwo.mactwocommandservice.infrastructure.mapper;

import com.mactwo.event.ProductCreatedEvent;
import com.mactwo.event.ProductUpdatedEvent;
import com.mactwo.event.StockUpdatedEvent;
import com.mactwo.mactwocommandservice.domain.model.Product;
import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Chuyển đổi từ các Entity liên quan đến Product sang các Event để gửi đi Kafka.
 */
@Mapper(componentModel = "spring")
public interface ProductEventMapper {

    // --- Mapping cho ProductCreatedEvent ---
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductCreatedEvent toProductCreatedEvent(Product product);

    @Mapping(source = "id", target = "variantId")
    ProductCreatedEvent.VariantInfo createdEventVariantInfo(ProductVariant variant);

    List<ProductCreatedEvent.VariantInfo> createdEventVariantInfoList(List<ProductVariant> variants);

    // --- Mapping cho ProductUpdatedEvent ---
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductUpdatedEvent toProductUpdatedEvent(Product product);

    @Mapping(source = "id", target = "variantId")
    ProductUpdatedEvent.VariantInfo updatedEventVariantInfo(ProductVariant variant);

    List<ProductUpdatedEvent.VariantInfo> updatedEventVariantInfoList(List<ProductVariant> variants);

    // --- Mapping cho StockUpdatedEvent ---
    @Mapping(source = "id", target = "variantId")
    @Mapping(source = "stockQuantity", target = "newStockQuantity")
    @Mapping(target = "quantityChange", ignore = true) // Sẽ được set trong service
    StockUpdatedEvent toStockUpdatedEvent(ProductVariant variant);
}
