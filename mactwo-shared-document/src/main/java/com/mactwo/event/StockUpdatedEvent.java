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
public class StockUpdatedEvent extends EventBase {
    private Long variantId;
    private int newStockQuantity;
    private int quantityChange; // Số lượng thay đổi (ví dụ: -2 khi khách mua 2 sản phẩm)
}
