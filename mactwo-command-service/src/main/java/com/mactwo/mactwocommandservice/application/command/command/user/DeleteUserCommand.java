package com.mactwo.mactwocommandservice.application.command.command.user;

import lombok.Getter;

@Getter
public class DeleteUserCommand {
    private final Long userId;
    public DeleteUserCommand(Long userId) { this.userId = userId; }
}
