package com.mactwo.command.application.command.promotion;

import lombok.Getter;

@Getter
public class DeletePromotionCommand {
    private final Long promotionId;
    public DeletePromotionCommand(Long promotionId) { this.promotionId = promotionId; }
}