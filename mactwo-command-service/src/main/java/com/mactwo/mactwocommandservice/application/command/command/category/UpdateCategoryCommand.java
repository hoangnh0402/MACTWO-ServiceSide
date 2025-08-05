package com.mactwo.mactwocommandservice.application.command.command.category;

import com.mactwo.dto.request.category.CategoryRequest;
import lombok.Getter;

@Getter
public class UpdateCategoryCommand {
    private final Long categoryId;
    private final String categoryName;
    private final Long parentId;

    public UpdateCategoryCommand(Long categoryId, CategoryRequest request) {
        this.categoryId = categoryId;
        this.categoryName = request.getCategoryName();
        this.parentId = request.getParentId();
    }
}
