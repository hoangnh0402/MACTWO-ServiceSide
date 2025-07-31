package com.mactwo.dto.request.product;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductRequest {
    private String name;
    private String brand;
    private String description;
    private Long categoryId;
    private List<VariantDTO> variants;

    @Data
    public static class VariantDTO {
        private String sku;
        private String color;
        private String storage;
        private String ram;
        private BigDecimal price;
        private int stockQuantity;
    }
}
