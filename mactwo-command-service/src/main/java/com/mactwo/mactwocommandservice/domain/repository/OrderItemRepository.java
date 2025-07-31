package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.OrderItem;

public interface OrderItemRepository {
    OrderItem save(OrderItem orderItem);
}
