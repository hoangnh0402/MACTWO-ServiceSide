package com.mactwo.mactwocommandservice.domain.repository;

import com.mactwo.mactwocommandservice.domain.model.Article;

import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    void deleteById(Long id);
}
