package com.mactwo.command.application.command.promotion;

import com.mactwo.document.dto.request.promotion.PromotionRequest;
import lombok.Getter;

@Getter
public class UpdatePromotionCommand {
    private final Long promotionId;
    private final PromotionRequest request;

    public UpdatePromotionCommand(Long promotionId, PromotionRequest request) {
        this.promotionId = promotionId;
        this.request = request;
    }
}