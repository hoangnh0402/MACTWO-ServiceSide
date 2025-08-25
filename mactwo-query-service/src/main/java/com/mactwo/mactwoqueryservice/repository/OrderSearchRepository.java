package com.mactwo.query.repository;

import com.mactwo.query.document.OrderDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderSearchRepository extends ElasticsearchRepository<OrderDocument, Long> {
    Page<OrderDocument> findByUserId(Long userId, Pageable pageable);
}