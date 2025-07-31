package com.mactwo.mactwocommandservice.domain.model;

import com.mactwo.mactwocommandservice.domain.model.base.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
public class User extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String passwordHash;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String gender;
}
