package com.mactwo.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PromotionResponse {
    private Long id;
    private String promotionName;
    private BigDecimal discountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
