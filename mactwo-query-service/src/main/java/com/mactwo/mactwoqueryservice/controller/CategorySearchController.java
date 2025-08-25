package com.mactwo.query.controller;

import com.mactwo.query.controller.base.RestApiV1;
import com.mactwo.query.controller.base.RestData;
import com.mactwo.query.controller.base.VsResponseUtil;
import com.mactwo.query.service.CategorySearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestApiV1
@RequestMapping("/categories")
@Tag(name = "Category Search & Query")
@RequiredArgsConstructor
public class CategorySearchController {

    private final CategorySearchService categorySearchService;

    @GetMapping
    public ResponseEntity<RestData<?>> getAllCategories() {
        return VsResponseUtil.ok(categorySearchService.findAllCategories());
    }
}