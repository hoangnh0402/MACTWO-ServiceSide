package com.mactwo.mactwocommandservice.application.command.command.auth;

import lombok.Getter;

@Getter
public class LogoutCommand {
    private final String jwtToken;

    public LogoutCommand(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
