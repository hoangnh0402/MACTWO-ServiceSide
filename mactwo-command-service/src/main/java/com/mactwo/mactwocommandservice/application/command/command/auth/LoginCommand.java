package com.mactwo.mactwocommandservice.application.command.command.auth;

import com.mactwo.dto.request.auth.LoginRequest;
import lombok.Getter;

@Getter
public class LoginCommand {
    private final String email;
    private final String password;

    public LoginCommand(LoginRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}
