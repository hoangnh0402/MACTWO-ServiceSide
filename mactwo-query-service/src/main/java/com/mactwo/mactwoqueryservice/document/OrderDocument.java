package com.mactwo.query.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(indexName = "orders")
public class OrderDocument {
    @Id
    private Long orderId;
    @Field(type = FieldType.Long, name = "userId")
    private Long userId;
    @Field(type = FieldType.Date, name = "orderDate")
    private LocalDateTime orderDate;
    @Field(type = FieldType.Keyword, name = "status")
    private String status;
    @Field(type = FieldType.Double, name = "totalAmount")
    private BigDecimal totalAmount;
    @Field(type = FieldType.Text, name = "shippingAddress")
    private String shippingAddress;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<OrderItem> items;

    @Data
    public static class OrderItem {
        @Field(type = FieldType.Long, name = "variantId")
        private Long variantId;
        @Field(type = FieldType.Integer, name = "quantity")
        private int quantity;
        @Field(type = FieldType.Double, name = "pricePerItem")
        private BigDecimal pricePerItem;
    }
}