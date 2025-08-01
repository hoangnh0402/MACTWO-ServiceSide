package com.mactwo.mactwocommandservice.application.command.handler;

public interface Handler <T>{
    void execute(T command);
}

