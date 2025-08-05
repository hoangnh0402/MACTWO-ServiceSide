package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    boolean existsByCategoryId(Long categoryId);
}