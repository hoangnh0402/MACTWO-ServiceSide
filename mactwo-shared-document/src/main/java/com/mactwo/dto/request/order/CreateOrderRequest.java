package com.mactwo.dto.request.order;

import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    private Long addressId;
    private Long promotionId;
    private List<OrderItemDTO> items;

    @Data
    public static class OrderItemDTO {
        private Long variantId;
        private int quantity;
    }
}
