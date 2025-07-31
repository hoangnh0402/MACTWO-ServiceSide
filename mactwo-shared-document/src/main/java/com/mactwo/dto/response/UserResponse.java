package com.mactwo.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
