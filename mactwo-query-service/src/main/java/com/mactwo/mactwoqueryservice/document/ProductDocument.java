package com.mactwo.query.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;


@Data
@Document(indexName = "products")
public class ProductDocument {

    @Id
    private Long productId;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Long, name = "categoryId")
    private Long categoryId;

    @Field(type = FieldType.Keyword, name = "imageUrl")
    private String imageUrl;

    // Lưu trữ các biến thể dưới dạng một đối tượng lồng nhau
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Variant> variants;

    @Data
    public static class Variant {
        @Field(type = FieldType.Long, name = "variantId")
        private Long variantId;

        @Field(type = FieldType.Keyword, name = "color")
        private String color;

        @Field(type = FieldType.Keyword, name = "storage")
        private String storage;

        @Field(type = FieldType.Keyword, name = "ram")
        private String ram;

        @Field(type = FieldType.Double, name = "price")
        private BigDecimal price;

        @Field(type = FieldType.Integer, name = "stockQuantity")
        private int stockQuantity;

        @Field(type = FieldType.Keyword, name = "imageUrls")
        private List<String> imageUrls;
    }
}