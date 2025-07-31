package com.mactwo.mactwocommandservice.infrastructure.persistence.repository.impl;

import com.mactwo.mactwocommandservice.domain.model.Article;
import com.mactwo.mactwocommandservice.domain.repository.ArticleRepository;
import com.mactwo.mactwocommandservice.infrastructure.persistence.repository.JpaArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ArticleRepositoryJpaImpl implements ArticleRepository {
    private final JpaArticleRepository jpaRepository;

    public ArticleRepositoryJpaImpl(JpaArticleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Article save(Article article) {
        return jpaRepository.save(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}