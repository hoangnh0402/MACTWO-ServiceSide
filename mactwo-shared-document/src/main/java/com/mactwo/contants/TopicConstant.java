package com.mactwo.contants;

/**
 * Quản lý tên các chủ đề (topic) trên Kafka.
 * Mỗi hành động làm thay đổi dữ liệu quan trọng sẽ có một topic riêng.
 */
public final class TopicConstant {
    private TopicConstant() {}

    public static class ProductTopic {
        public static final String PRODUCT_CREATED = "product-created-event";
        public static final String PRODUCT_UPDATED = "product-updated-event";
        public static final String PRODUCT_DELETED = "product-deleted-event";
        public static final String STOCK_UPDATED = "product-stock-updated-event";
    }

    public static class CategoryTopic {
        public static final String CATEGORY_CREATED = "category-created-event";
        public static final String CATEGORY_UPDATED = "category-updated-event";
        public static final String CATEGORY_DELETED = "category-deleted-event";
    }

    public static class OrderTopic {
        /**
         * Sự kiện khi một đơn hàng được đặt thành công.
         */
        public static final String ORDER_PLACED = "order-placed-event";
        /**
         * Sự kiện khi trạng thái của một đơn hàng thay đổi (ví dụ: từ đang xử lý sang đã giao).
         */
        public static final String ORDER_STATUS_UPDATED = "order-status-updated-event";
    }

    public static class UserTopic {
        public static final String USER_REGISTERED = "user-registered-event";
        public static final String USER_PROFILE_UPDATED = "user-profile-updated-event";
        public static final String USER_ADDRESS_CHANGED = "user-address-changed-event";
    }

    public static class PromotionTopic {
        public static final String PROMOTION_CREATED = "promotion-created-event";
        public static final String PROMOTION_UPDATED = "promotion-updated-event";
        public static final String PROMOTION_DELETED = "promotion-deleted-event";
    }
}
