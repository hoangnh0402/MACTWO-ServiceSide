package com.mactwo.mactwocommandservice.domain.model;

import com.mactwo.mactwocommandservice.domain.model.base.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String ram;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;
}
