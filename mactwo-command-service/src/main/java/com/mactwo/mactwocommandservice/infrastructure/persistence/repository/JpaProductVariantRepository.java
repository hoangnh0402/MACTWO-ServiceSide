package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
