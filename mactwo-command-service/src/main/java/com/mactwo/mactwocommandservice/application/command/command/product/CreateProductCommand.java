package com.mactwo.mactwocommandservice.application.command.command.product;

import com.mactwo.dto.request.product.CreateProductRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class CreateProductCommand {
    private final String name;
    private final String description;
    private final Long categoryId;
    private final String imageUrl; // SỬA LỖI: Thêm trường imageUrl
    private final List<CreateProductRequest.VariantDTO> variants;

    public CreateProductCommand(CreateProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.categoryId = request.getCategoryId();
        this.imageUrl = request.getImageUrl(); // SỬA LỖI: Gán giá trị
        this.variants = request.getVariants();
    }
}
