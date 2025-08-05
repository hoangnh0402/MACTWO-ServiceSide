package com.mactwo.dto.request.category;

import lombok.Data;

@Data
public class CategoryRequest {
    private String categoryName;
    private Long parentId; // ID của danh mục cha, có thể là null nếu là danh mục gốc
}
