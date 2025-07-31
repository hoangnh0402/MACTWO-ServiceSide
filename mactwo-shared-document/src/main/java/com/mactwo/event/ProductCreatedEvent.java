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
public class ProductCreatedEvent extends EventBase {
    private Long productId;
    private String name;
    private String brand;
    private String description;
    private Long categoryId;
    private List<VariantInfo> variants;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VariantInfo {
        private Long variantId;
        private String sku;
        private String color;
        private String storage;
        private String ram;
        private BigDecimal price;
        private int stockQuantity;
    }
}
