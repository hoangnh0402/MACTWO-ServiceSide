package com.mactwo.mactwocommandservice.infrastructure.mapper;

import com.mactwo.mactwocommandservice.application.command.command.category.CreateCategoryCommand;
import com.mactwo.mactwocommandservice.application.command.command.category.UpdateCategoryCommand;
import com.mactwo.mactwocommandservice.domain.model.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    Category toCategory(CreateCategoryCommand command);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    void updateCategoryFromCommand(UpdateCategoryCommand command, @MappingTarget Category category);
}
