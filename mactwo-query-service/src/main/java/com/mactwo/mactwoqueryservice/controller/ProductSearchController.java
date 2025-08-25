package com.mactwo.query.controller;

import com.mactwo.document.dto.response.ProductResponse;
import com.mactwo.query.controller.base.RestApiV1;
import com.mactwo.query.service.ProductSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RestApiV1
@RequestMapping("/products")
@Tag(name = "Product Search")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            Pageable pageable) {

        Page<ProductResponse> results = productSearchService.searchProducts(keyword, categoryId, minPrice, maxPrice, pageable);
        return ResponseEntity.ok(results);
    }
}
