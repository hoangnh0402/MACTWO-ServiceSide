package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.ProductVariant;

import java.util.Optional;

public interface ProductVariantRepository {
    ProductVariant save(ProductVariant variant);
    Optional<ProductVariant> findById(Long id);
}
