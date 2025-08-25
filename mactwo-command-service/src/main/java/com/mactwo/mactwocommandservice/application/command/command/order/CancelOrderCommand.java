package com.mactwo.command.application.command.order;

import lombok.Getter;

@Getter
public class CancelOrderCommand {
    private final Long orderId;
    private final Long userId; // ID của người dùng thực hiện hành động (để kiểm tra quyền)

    public CancelOrderCommand(Long orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}