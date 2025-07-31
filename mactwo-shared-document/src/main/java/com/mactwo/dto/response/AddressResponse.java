package com.mactwo.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
    private Long id;
    private String shippingAddress;
    private boolean isDefault;
}
