package com.mactwo.mactwocommandservice.application.command.command.product;

import com.mactwo.dto.request.product.UpdateProductRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class UpdateProductCommand {
    private final Long productId;
    private final String name;
    private final String description;
    private final Long categoryId;
    private final String imageUrl; // SỬA LỖI: Thêm trường imageUrl
    private final List<UpdateProductRequest.VariantDTO> variants;

    public UpdateProductCommand(Long productId, UpdateProductRequest request) {
        this.productId = productId;
        this.name = request.getName();
        this.description = request.getDescription();
        this.categoryId = request.getCategoryId();
        this.imageUrl = request.getImageUrl(); // SỬA LỖI: Gán giá trị
        this.variants = request.getVariants();
    }
}
