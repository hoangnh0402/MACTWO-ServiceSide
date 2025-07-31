package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPromotionRepository extends JpaRepository<Promotion, Long> {
}
