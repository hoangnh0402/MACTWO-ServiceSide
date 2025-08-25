package com.mactwo.command.application.command.address;

import com.mactwo.document.dto.request.address.AddressRequest;
import lombok.Getter;

@Getter
public class AddAddressCommand {
    private final Long userId;
    private final AddressRequest request;

    public AddAddressCommand(Long userId, AddressRequest request) {
        this.userId = userId;
        this.request = request;
    }
}