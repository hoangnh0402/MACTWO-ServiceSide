package com.mactwo.dto.request.product;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private Long categoryId;
    private String imageUrl; // Ảnh đại diện cho sản phẩm
    private List<VariantDTO> variants;

    @Data
    public static class VariantDTO {
        private Long id;
        private String color;
        private String storage;
        private String ram;
        private BigDecimal price;
        private int stockQuantity;
        private List<String> imageUrls; // Danh sách ảnh cho từng biến thể
    }
}
