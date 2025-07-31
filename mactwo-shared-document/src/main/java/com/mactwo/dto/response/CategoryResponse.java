package com.mactwo.dto.response;

import lombok.Data;
import java.util.Set;

@Data
public class CategoryResponse {
    private Long id;
    private String categoryName;
    private Long parentId;
    private Set<CategoryResponse> children;
}
