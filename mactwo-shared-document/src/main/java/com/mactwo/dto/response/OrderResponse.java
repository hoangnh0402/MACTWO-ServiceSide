package com.mactwo.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;
    private AddressInfo address;
    private List<OrderItemResponse> items;

    @Data
    public static class AddressInfo {
        private Long id;
        private String shippingAddress;
    }

    @Data
    public static class OrderItemResponse {
        private Long variantId;
        private String productName;
        private String variantInfo; // Ví dụ: "Xám, 256GB, 16GB RAM"
        private int quantity;
        private BigDecimal pricePerItem;
    }
}
