package com.mactwo.dto.response;

import lombok.Data;

@Data
public class BannerResponse {
    private Long id;
    private String imageUrl;
    private String linkUrl;
    private int displayOrder;
    private boolean isActive;
}
