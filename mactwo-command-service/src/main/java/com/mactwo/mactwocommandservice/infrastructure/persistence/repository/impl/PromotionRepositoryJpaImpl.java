package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.Promotion;
import com.mactwo.mactwocommandservice.domain.repository.PromotionRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaPromotionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PromotionRepositoryJpaImpl implements PromotionRepository {
    private final JpaPromotionRepository jpaRepository;

    public PromotionRepositoryJpaImpl(JpaPromotionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Promotion save(Promotion promotion) {
        return jpaRepository.save(promotion);
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return jpaRepository.findById(id);
    }
}