package com.mactwo.mactwocommandservice.application.command.command.user;

import lombok.Getter;

@Getter
public class DeactivateUserCommand {
    private final Long userId;
    public DeactivateUserCommand(Long userId) { this.userId = userId; }
}
