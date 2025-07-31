package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.Product;
import com.mactwo.mactwocommandservice.domain.repository.ProductRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryJpaImpl implements ProductRepository {
    private final JpaProductRepository jpaRepository;

    public ProductRepositoryJpaImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        return jpaRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
