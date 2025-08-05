package com.mactwo.mactwocommandservice.application.command.command.product;

import lombok.Getter;

@Getter
public class UpdateStockCommand {
    private final Long variantId;
    private final int newStockQuantity;

    public UpdateStockCommand(Long variantId, int newStockQuantity) {
        this.variantId = variantId;
        this.newStockQuantity = newStockQuantity;
    }
}
