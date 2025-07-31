package com.mactwo.dto.request.article;

import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String content;
    private String featuredImageUrl;
    private String slug;
    private Long categoryId;
    private Long authorId;
}
