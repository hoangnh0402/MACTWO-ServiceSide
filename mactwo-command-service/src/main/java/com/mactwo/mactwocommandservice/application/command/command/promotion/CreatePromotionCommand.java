package com.mactwo.command.application.command.promotion;

import com.mactwo.document.dto.request.promotion.PromotionRequest;
import lombok.Getter;

@Getter
public class CreatePromotionCommand {
    private final PromotionRequest request;
    public CreatePromotionCommand(PromotionRequest request) { this.request = request; }
}