package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatusUpdatedEvent extends EventBase {
    private Long orderId;
    private String newStatus;
    private String oldStatus;
    private Long userId;
}
