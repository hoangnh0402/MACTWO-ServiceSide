package com.mactwo.query.repository;

import com.mactwo.query.document.CategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategorySearchRepository extends ElasticsearchRepository<CategoryDocument, Long> {
}