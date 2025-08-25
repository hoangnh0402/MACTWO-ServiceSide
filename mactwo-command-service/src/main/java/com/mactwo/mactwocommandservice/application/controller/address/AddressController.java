package com.mactwo.command.application.controller;

import com.mactwo.command.application.base.RestApiV1;
import com.mactwo.command.application.base.RestData;
import com.mactwo.command.application.base.VsResponseUtil;
import com.mactwo.command.application.command.address.AddAddressCommand;
import com.mactwo.command.application.command.address.DeleteAddressCommand;
import com.mactwo.command.application.command.address.UpdateAddressCommand;
import com.mactwo.command.application.command.handler.address.AddAddressCommandHandler;
import com.mactwo.command.application.command.handler.address.DeleteAddressCommandHandler;
import com.mactwo.command.application.command.handler.address.UpdateAddressCommandHandler;
import com.mactwo.command.infrastructure.config.CustomUserDetails;
import com.mactwo.document.dto.request.address.AddressRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/addresses")
@Tag(name = "8. Address Management (User)")
@RequiredArgsConstructor
public class AddressController {

    private final AddAddressCommandHandler addAddressCommandHandler;
    private final UpdateAddressCommandHandler updateAddressCommandHandler;
    private final DeleteAddressCommandHandler deleteAddressCommandHandler;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestData<?>> addAddress(@RequestBody AddressRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var command = new AddAddressCommand(userDetails.getId(), request);
        var address = addAddressCommandHandler.handle(command);
        return VsResponseUtil.ok(HttpStatus.CREATED, address);
    }

    @PutMapping("/{addressId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestData<?>> updateAddress(@PathVariable Long addressId, @RequestBody AddressRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var command = new UpdateAddressCommand(userDetails.getId(), addressId, request);
        var address = updateAddressCommandHandler.handle(command);
        return VsResponseUtil.ok(address);
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestData<?>> deleteAddress(@PathVariable Long addressId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var command = new DeleteAddressCommand(userDetails.getId(), addressId);
        deleteAddressCommandHandler.handle(command);
        return VsResponseUtil.ok("Address deleted successfully.");
    }
}