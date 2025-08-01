package com.mactwo.mactwocommandservice.application.command.command.user;

import lombok.Getter;

@Getter
public class ForgotPasswordCommand {
    private final String email;

    public ForgotPasswordCommand(String email) {
        this.email = email;
    }
}
