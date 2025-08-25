package com.mactwo.query.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "categories")
public class CategoryDocument {
    @Id
    private Long categoryId;
    @Field(type = FieldType.Text, name = "categoryName")
    private String categoryName;
    @Field(type = FieldType.Long, name = "parentId")
    private Long parentId;
}