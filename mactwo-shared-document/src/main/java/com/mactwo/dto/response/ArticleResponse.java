package com.mactwo.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String slug;
    private String featuredImageUrl;
    private UserInfo author;
    private CategoryInfo category;
    private LocalDateTime publishedAt;

    @Data
    public static class UserInfo {
        private Long id;
        private String fullName;
    }

    @Data
    public static class CategoryInfo {
        private Long id;
        private String categoryName;
    }
}
