package com.mactwo.mactwocommandservice.application.command.command.user;

import com.mactwo.dto.request.user.UserRegisterRequest;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterUserCommand {
    private final String fullName;
    private final String email;
    private final String password;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final String gender;
    public RegisterUserCommand(UserRegisterRequest request) {
        this.fullName = request.getFullName();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.phoneNumber = request.getPhoneNumber();
        this.dateOfBirth = request.getDateOfBirth();
        this.gender = request.getGender();
    }
}