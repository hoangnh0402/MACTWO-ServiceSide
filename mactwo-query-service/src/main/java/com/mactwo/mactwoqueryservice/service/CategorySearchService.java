package com.mactwo.query.service;

import com.mactwo.document.dto.response.CategoryResponse;
import com.mactwo.query.mapper.CategoryQueryMapper;
import com.mactwo.query.repository.CategorySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategorySearchService {
    private final CategorySearchRepository categorySearchRepository;
    private final CategoryQueryMapper categoryQueryMapper;

    public List<CategoryResponse> findAllCategories() {
        return StreamSupport.stream(categorySearchRepository.findAll().spliterator(), false)
                .map(categoryQueryMapper::toResponse)
                .collect(Collectors.toList());
    }
}