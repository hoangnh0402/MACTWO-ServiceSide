package com.mactwo.mactwocommandservice.application.command.handler;


public interface HandlerDTO<T, U> {

    U execute(T command);
}
