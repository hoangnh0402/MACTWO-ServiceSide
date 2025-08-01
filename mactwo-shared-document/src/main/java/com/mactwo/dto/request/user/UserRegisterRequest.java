package com.mactwo.dto.request.user;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
}
