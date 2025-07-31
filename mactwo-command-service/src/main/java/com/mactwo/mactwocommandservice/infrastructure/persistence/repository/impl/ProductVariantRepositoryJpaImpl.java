package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import com.mactwo.mactwocommandservice.domain.repository.ProductVariantRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaProductVariantRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductVariantRepositoryJpaImpl implements ProductVariantRepository {
    private final JpaProductVariantRepository jpaRepository;

    public ProductVariantRepositoryJpaImpl(JpaProductVariantRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ProductVariant save(ProductVariant variant) {
        return jpaRepository.save(variant);
    }

    @Override
    public Optional<ProductVariant> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
