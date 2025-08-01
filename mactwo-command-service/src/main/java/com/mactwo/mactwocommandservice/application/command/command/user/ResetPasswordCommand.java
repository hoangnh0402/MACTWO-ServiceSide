package com.mactwo.mactwocommandservice.application.command.command.user;

import com.mactwo.dto.request.user.ResetPasswordRequest;
import lombok.Getter;

@Getter
public class ResetPasswordCommand {
    private final String email;
    private final String otp;
    private final String newPassword;

    public ResetPasswordCommand(ResetPasswordRequest request) {
        this.email = request.getEmail();
        this.otp = request.getOtp();
        this.newPassword = request.getNewPassword();
    }
}
