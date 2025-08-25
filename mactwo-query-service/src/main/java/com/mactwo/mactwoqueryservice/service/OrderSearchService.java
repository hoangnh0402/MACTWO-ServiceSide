package com.mactwo.query.service;

import com.mactwo.document.dto.response.OrderResponse;
import com.mactwo.query.mapper.OrderQueryMapper;
import com.mactwo.query.repository.OrderSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSearchService {
    private final OrderSearchRepository orderSearchRepository;
    private final OrderQueryMapper orderQueryMapper;

    public Page<OrderResponse> findOrdersByUserId(Long userId, Pageable pageable) {
        return orderSearchRepository.findByUserId(userId, pageable).map(orderQueryMapper::toResponse);
    }
}