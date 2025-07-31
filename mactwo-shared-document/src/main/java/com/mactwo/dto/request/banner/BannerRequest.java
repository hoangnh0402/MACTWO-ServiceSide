package com.mactwo.dto.request.banner;

import lombok.Data;

@Data
public class BannerRequest {
    private Long categoryId;
    private String imageUrl;
    private String linkUrl;
    private int displayOrder;
    private boolean isActive;
}
