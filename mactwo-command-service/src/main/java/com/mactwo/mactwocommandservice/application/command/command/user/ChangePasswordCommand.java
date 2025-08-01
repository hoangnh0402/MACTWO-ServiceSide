package com.mactwo.mactwocommandservice.application.command.command.user;

import com.mactwo.dto.request.user.ChangePasswordRequest;
import lombok.Getter;

@Getter
public class ChangePasswordCommand {
    private final Long userId;
    private final String oldPassword;
    private final String newPassword;

    public ChangePasswordCommand(Long userId, ChangePasswordRequest request) {
        this.userId = userId;
        this.oldPassword = request.getOldPassword();
        this.newPassword = request.getNewPassword();
    }
}
