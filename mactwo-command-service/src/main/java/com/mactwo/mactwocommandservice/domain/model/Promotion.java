package com.mactwo.mactwocommandservice.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
@Data
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    private String promotionName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal discountAmount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
