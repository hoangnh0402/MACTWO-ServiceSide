package com.mactwo.query.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.time.LocalDate;

@Data
@Document(indexName = "users")
public class UserDocument {
    @Id
    private Long userId;
    @Field(type = FieldType.Text, name = "fullName")
    private String fullName;
    @Field(type = FieldType.Keyword, name = "email")
    private String email;
    @Field(type = FieldType.Keyword, name = "phoneNumber")
    private String phoneNumber;
    @Field(type = FieldType.Date, name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Field(type = FieldType.Boolean, name = "active")
    private boolean active;
}