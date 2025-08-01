package com.mactwo.mactwocommandservice.domain.model;

import com.mactwo.mactwocommandservice.domain.model.base.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<ProductVariant> variants = new ArrayList<>();

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;
}
