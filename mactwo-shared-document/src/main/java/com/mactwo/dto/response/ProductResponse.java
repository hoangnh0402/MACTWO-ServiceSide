package com.mactwo.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryInfo category;
    private List<VariantResponse> variants;

    @Data
    public static class CategoryInfo {
        private Long id;
        private String name;
    }

    @Data
    public static class VariantResponse {
        private Long id;
        private String sku;
        private String color;
        private String storage;
        private String ram;
        private BigDecimal price;
    }
}
