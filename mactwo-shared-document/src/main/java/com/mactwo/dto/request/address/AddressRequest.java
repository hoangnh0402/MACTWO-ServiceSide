package com.mactwo.dto.request.address;

import lombok.Data;

@Data
public class AddressRequest {
    private String shippingAddress;
    private boolean isDefault;
}
