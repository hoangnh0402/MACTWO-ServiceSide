package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderPlacedEvent extends EventBase {
    private Long orderId;
    private Long userId;
    private String userFullName;
    private String shippingAddress;
    private BigDecimal totalAmount;
    private Long promotionId; // Có thể null
    private List<OrderItemInfo> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemInfo {
        private Long variantId;
        private String sku;
        private String productName;
        private int quantity;
        private BigDecimal pricePerItem;
    }
}
