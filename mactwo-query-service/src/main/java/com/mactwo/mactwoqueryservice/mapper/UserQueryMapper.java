package com.mactwo.query.mapper;

import com.mactwo.document.dto.response.UserResponse;
import com.mactwo.query.document.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQueryMapper {
    @Mapping(source = "userId", target = "id")
    UserResponse toResponse(UserDocument document);
}