package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategoryId(Long categoryId);
}
