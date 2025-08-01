package com.mactwo.mactwocommandservice.application.command.command.user;

import com.mactwo.dto.request.user.UpdateProfileRequest;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateProfileCommand {
    private final Long userId;
    private final String fullName;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final String gender;

    public UpdateProfileCommand(Long userId, UpdateProfileRequest request) {
        this.userId = userId;
        this.fullName = request.getFullName();
        this.phoneNumber = request.getPhoneNumber();
        this.dateOfBirth = request.getDateOfBirth();
        this.gender = request.getGender();
    }
}
