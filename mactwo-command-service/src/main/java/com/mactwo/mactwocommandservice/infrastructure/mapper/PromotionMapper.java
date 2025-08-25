package com.mactwo.command.infrastructure.mapper;

import com.mactwo.command.domain.model.Promotion;
import com.mactwo.document.dto.request.promotion.PromotionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(target = "id", ignore = true)
    Promotion toPromotion(PromotionRequest request);
}