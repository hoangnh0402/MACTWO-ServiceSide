package com.mactwo.query.service;

import com.mactwo.document.dto.response.UserResponse;
import com.mactwo.query.mapper.UserQueryMapper;
import com.mactwo.query.repository.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchService {
    private final UserSearchRepository userSearchRepository;
    private final UserQueryMapper userQueryMapper;

    public Page<UserResponse> findAllUsers(Pageable pageable) {
        return userSearchRepository.findAll(pageable).map(userQueryMapper::toResponse);
    }
}