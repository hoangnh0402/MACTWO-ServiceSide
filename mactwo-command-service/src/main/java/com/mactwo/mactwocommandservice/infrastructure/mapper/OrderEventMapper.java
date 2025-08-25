package com.mactwo.mactwocommandservice.infrastructure.mapper;

import com.mactwo.command.domain.model.Order;
import com.mactwo.command.domain.model.OrderItem;
import com.mactwo.document.event.order.OrderPlacedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderEventMapper {
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userFullName")
    @Mapping(source = "address.shippingAddress", target = "shippingAddress")
    @Mapping(source = "promotion.id", target = "promotionId")
    OrderPlacedEvent toOrderPlacedEvent(Order order);

    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "price", target = "pricePerItem")
    OrderPlacedEvent.OrderItemInfo toOrderItemInfo(OrderItem orderItem);

    List<OrderPlacedEvent.OrderItemInfo> toOrderItemInfoList(List<OrderItem> orderItems);
}