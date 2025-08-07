package com.mactwo.mactwocommandservice.application.command.command.category;

import com.mactwo.dto.request.category.CategoryRequest;
import lombok.Getter;

@Getter
public class CreateCategoryCommand {
    private final String categoryName;
    private final Long parentId;

    public CreateCategoryCommand(CategoryRequest request) {
        this.categoryName = request.getCategoryName();
        this.parentId = request.getParentId();
    }
}

