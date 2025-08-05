package com.mactwo.mactwocommandservice.application.command.command.product;

import lombok.Getter;

@Getter
public class DeleteProductCommand {
    private final Long productId;
    public DeleteProductCommand(Long productId) { this.productId = productId; }
}
