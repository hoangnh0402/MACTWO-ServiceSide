package com.mactwo.query.mapper;

import com.mactwo.document.dto.response.CategoryResponse;
import com.mactwo.query.document.CategoryDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryQueryMapper {
    @Mapping(source = "categoryId", target = "id")
    CategoryResponse toResponse(CategoryDocument document);
}