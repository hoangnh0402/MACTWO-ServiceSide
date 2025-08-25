package com.mactwo.query.controller;

import com.mactwo.query.controller.base.RestApiV1;
import com.mactwo.query.controller.base.RestData;
import com.mactwo.query.controller.base.VsResponseUtil;
import com.mactwo.query.service.UserSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestApiV1
@RequestMapping("/users")
@Tag(name = "User Search & Query")
@RequiredArgsConstructor
public class UserSearchController {
    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<RestData<?>> getAllUsers(Pageable pageable) {
        return VsResponseUtil.ok(userSearchService.findAllUsers(pageable));
    }
}