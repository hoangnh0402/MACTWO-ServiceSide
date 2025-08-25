package com.mactwo.query.controller;

import com.mactwo.query.controller.base.RestApiV1;
import com.mactwo.query.controller.base.RestData;
import com.mactwo.query.controller.base.VsResponseUtil;
import com.mactwo.query.service.OrderSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestApiV1
@RequestMapping("/orders")
@Tag(name = "Order Search & Query")
@RequiredArgsConstructor
public class OrderSearchController {
    private final OrderSearchService orderSearchService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestData<?>> getOrdersByUserId(@PathVariable Long userId, Pageable pageable) {
        return VsResponseUtil.ok(orderSearchService.findOrdersByUserId(userId, pageable));
    }
}