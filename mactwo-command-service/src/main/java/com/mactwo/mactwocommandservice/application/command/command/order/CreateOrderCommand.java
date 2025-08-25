package com.mactwo.command.application.command.order;

import com.mactwo.document.dto.request.order.CreateOrderRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class CreateOrderCommand {
    private final Long userId; // ID của người dùng đang đăng nhập
    private final Long addressId;
    private final Long promotionId;
    private final List<CreateOrderRequest.OrderItemDTO> items;

    public CreateOrderCommand(Long userId, CreateOrderRequest request) {
        this.userId = userId;
        this.addressId = request.getAddressId();
        this.promotionId = request.getPromotionId();
        this.items = request.getItems();
    }
}