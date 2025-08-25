package com.mactwo.command.application.command.order;

import lombok.Getter;

@Getter
public class UpdateOrderStatusCommand {
    private final Long orderId;
    private final String newStatus;

    public UpdateOrderStatusCommand(Long orderId, String newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }
}