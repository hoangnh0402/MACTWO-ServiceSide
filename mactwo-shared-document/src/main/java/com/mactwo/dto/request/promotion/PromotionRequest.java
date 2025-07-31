package com.mactwo.dto.request.promotion;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PromotionRequest {
    private String promotionName;
    private String description;
    private BigDecimal discountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
