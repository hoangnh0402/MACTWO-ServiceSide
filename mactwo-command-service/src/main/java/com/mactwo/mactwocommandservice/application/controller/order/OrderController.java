package com.mactwo.command.application.controller;

import com.mactwo.command.application.base.RestApiV1;
import com.mactwo.command.application.base.RestData;
import com.mactwo.command.application.base.VsResponseUtil;
import com.mactwo.command.application.command.handler.order.CancelOrderCommandHandler;
import com.mactwo.command.application.command.handler.order.CreateOrderCommandHandler;
import com.mactwo.command.application.command.handler.order.UpdateOrderStatusCommandHandler;
import com.mactwo.command.application.command.order.CancelOrderCommand;
import com.mactwo.command.application.command.order.CreateOrderCommand;
import com.mactwo.command.application.command.order.UpdateOrderStatusCommand;
import com.mactwo.command.infrastructure.config.CustomUserDetails;
import com.mactwo.document.dto.request.order.CreateOrderRequest;
import com.mactwo.document.dto.request.order.UpdateOrderStatusRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/orders")
@Tag(name = "7. Order Management")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderCommandHandler createOrderCommandHandler;
    private final UpdateOrderStatusCommandHandler updateOrderStatusCommandHandler;
    private final CancelOrderCommandHandler cancelOrderCommandHandler;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestData<?>> createOrder(@RequestBody CreateOrderRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var command = new CreateOrderCommand(userDetails.getId(), request);
        var order = createOrderCommandHandler.handle(command);
        return VsResponseUtil.ok(HttpStatus.CREATED, order);
    }

    @PatchMapping("/{orderId}/status")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<RestData<?>> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request) {
        var command = new UpdateOrderStatusCommand(orderId, request.getStatus());
        var order = updateOrderStatusCommandHandler.handle(command);
        return VsResponseUtil.ok(order);
    }

    @PostMapping("/{orderId}/cancel")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestData<?>> cancelOrder(@PathVariable Long orderId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var command = new CancelOrderCommand(orderId, userDetails.getId());
        cancelOrderCommandHandler.handle(command);
        return VsResponseUtil.ok("Order cancelled successfully.");
    }
}