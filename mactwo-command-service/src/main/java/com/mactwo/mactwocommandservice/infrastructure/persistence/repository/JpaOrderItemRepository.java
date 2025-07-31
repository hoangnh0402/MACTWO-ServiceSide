package com.mactwo.mactwocommandservice.infrastructure.persistence.repository;

import com.mactwo.mactwocommandservice.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderItemRepository extends JpaRepository<OrderItem, Long> {
}
