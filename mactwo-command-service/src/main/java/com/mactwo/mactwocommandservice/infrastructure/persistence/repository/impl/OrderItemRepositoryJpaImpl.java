package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.OrderItem;
import com.mactwo.mactwocommandservice.domain.repository.OrderItemRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaOrderItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepositoryJpaImpl implements OrderItemRepository {
    private final JpaOrderItemRepository jpaRepository;

    public OrderItemRepositoryJpaImpl(JpaOrderItemRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return jpaRepository.save(orderItem);
    }
}
