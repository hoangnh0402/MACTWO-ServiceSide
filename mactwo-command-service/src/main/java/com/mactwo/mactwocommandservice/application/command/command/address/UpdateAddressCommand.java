package com.mactwo.command.application.command.address;

import com.mactwo.document.dto.request.address.AddressRequest;
import lombok.Getter;

@Getter
public class UpdateAddressCommand {
    private final Long userId;
    private final Long addressId;
    private final AddressRequest request;

    public UpdateAddressCommand(Long userId, Long addressId, AddressRequest request) {
        this.userId = userId;
        this.addressId = addressId;
        this.request = request;
    }
}