package com.mactwo.query.service;

import com.mactwo.document.dto.response.ProductResponse;
import com.mactwo.query.document.ProductDocument;
import com.mactwo.query.mapper.ProductQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ProductQueryMapper productQueryMapper;

    public Page<ProductResponse> searchProducts(String keyword, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Criteria criteria = new Criteria();

        // Thêm điều kiện tìm kiếm theo từ khóa (tên hoặc mô tả)
        if (StringUtils.hasText(keyword)) {
            criteria.and(new Criteria("name").contains(keyword).or("description").contains(keyword));
        }

        // Thêm điều kiện lọc theo danh mục
        if (categoryId != null) {
            criteria.and(new Criteria("categoryId").is(categoryId));
        }

        // Thêm điều kiện lọc theo khoảng giá (trên các biến thể)
        if (minPrice != null || maxPrice != null) {
            Criteria priceCriteria = new Criteria("variants.price");
            if (minPrice != null) {
                priceCriteria.greaterThanEqual(minPrice);
            }
            if (maxPrice != null) {
                priceCriteria.lessThanEqual(maxPrice);
            }
            // Sử dụng nested query để lọc chính xác
            criteria.and(new Criteria().nest("variants", priceCriteria));
        }

        Query query = new CriteriaQuery(criteria, pageable);

        SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class);

        List<ProductResponse> responses = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(productQueryMapper::toResponse)
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(responses, pageable, searchHits::getTotalHits);
    }
}